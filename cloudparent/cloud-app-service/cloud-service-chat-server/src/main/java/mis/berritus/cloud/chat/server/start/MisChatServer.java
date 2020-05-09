package mis.berritus.cloud.chat.server.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/4/27
 */
public class MisChatServer {
    private static final Logger logger = LoggerFactory.getLogger(MisChatServer.class);

    private Selector selector;
    private ServerSocketChannel listenerChannel;
    //服务器端口
    private static final int PORT = 9999;

    private MisChatServer(){
    }

    private static class SingletonFactory{
        private static MisChatServer instance = new MisChatServer();
    }

    public static MisChatServer getInstance(){
        return SingletonFactory.instance;
    }

    private Object readResolve(){
        return getInstance();
    }

    public void init() {
        try {
            // 得到选择器
            selector = Selector.open();
            // 打开监听通道
            listenerChannel = ServerSocketChannel.open();
            // 绑定端口
            listenerChannel.bind(new InetSocketAddress(PORT));
            // 设置为非阻塞模式
            listenerChannel.configureBlocking(false);
            // 将选择器绑定到监听通道并监听 accept 事件
            listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
            printInfo("Chat Server is ready.......");
        } catch (IOException e) {
            logger.error("MisChatServer 初始化失败，{}", e);
        }
    }

    public void start() {
        try {
            //不停轮询
            while (true) {
                //获取就绪 channel
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        // 监听到 accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenerChannel.accept();
                            //非阻塞模式
                            sc.configureBlocking(false);
                            //注册到选择器上并监听 read
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress().toString().substring(1) + "上线了...");
                            //将此对应的 channel 设置为 accept,接着准备接受其他客户端请求
                            key.interestOps(SelectionKey.OP_ACCEPT);
                        }
                        //监听到 read
                        if (key.isReadable()) {
                            //读取客户端发来的数据
                            readMsg(key);
                        }
                        //一定要把当前 key 删掉，防止重复处理
                        iterator.remove();
                    }
                } else {
                    System.out.println("独自在寒风中等候...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMsg(SelectionKey key) {
        SocketChannel channel = null;
        try {
            // 得到关联的通道
            channel = (SocketChannel) key.channel();
            //设置 buffer 缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //从通道中读取数据并存储到缓冲区中
            int count = channel.read(buffer);
            //如果读取到了数据
            if (count > 0) {
                //把缓冲区数据转换为字符串
                String msg = new String(buffer.array());
                printInfo(msg);
                //将关联的 channel 设置为 read，继续准备接受数据
                key.interestOps(SelectionKey.OP_READ);
                BroadCast(channel, msg); //向所有客户端广播数据
            }
            buffer.clear();
        } catch (IOException e) {
            try {
                //当客户端关闭 channel 时，进行异常如理
                printInfo(channel.getRemoteAddress().toString().substring(1) + "下线了...");
                key.cancel(); //取消注册
                channel.close(); //关闭通道
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void BroadCast(SocketChannel except, String msg) throws IOException {
        System.out.println("发送广播...");
        //广播数据到所有的 SocketChannel 中
        for (SelectionKey key : selector.keys()) {
            Channel targetchannel = key.channel();
            //排除自身
            if (targetchannel instanceof SocketChannel && targetchannel != except) {
                SocketChannel dest = (SocketChannel) targetchannel;
                //把数据存储到缓冲区中
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //往通道中写数据
                dest.write(buffer);
            }
        }
    }

    private void printInfo(String str) {
        //往控制台打印消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("[" + sdf.format(new Date()) + "] -> " + str);
    }
}
