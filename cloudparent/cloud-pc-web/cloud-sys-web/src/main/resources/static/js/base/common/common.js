/**
 *
 * @param fommId 为表单ID
 */
function getFormData(fommId) {
    var obj = $("#" + fommId).serializeArray();
    var jsonObj = {};

    $.each(obj, function () {
        if (jsonObj[this.name] !== undefined) {
            if (!jsonObj[this.name].push) {
                jsonObj[this.name] = [jsonObj[this.name]];
            }
            jsonObj[this.name].push(this.value || '');
        } else {
            jsonObj[this.name] = this.value || '';
        }
    });

    return jsonObj;
}

function createTime(v) {
    var date = new Date(v);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    var d = date.getDate();
    d = d < 10 ? ("0" + d) : d;
    var h = date.getHours();
    h = h < 10 ? ("0" + h) : h;
    var M = date.getMinutes();
    M = M < 10 ? ("0" + M) : M;
    var str = y + "-" + m + "-" + d + " " + h + ":" + M;
    return str;
}

