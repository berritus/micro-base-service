package mis.berritus.cloud.dao;

/**
 *
 * @param <T>
 * @param <K>
 */
public interface MisDao<T, K> {
    int deleteByPrimaryKey(K id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(K id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
