// 添加Cookie中的列表集合
function addListToCookie(name, list) {
    var jsonStr = JSON.stringify(list);
    document.cookie = name + "=" + jsonStr + "; path=/";
}

// 从Cookie中获取列表集合
function getListFromCookie(name) {
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        if (cookie.indexOf(name + "=") === 0) {
            var jsonStr = cookie.substring(name.length + 1);
            return JSON.parse(jsonStr);
        }
    }
    return [];
}

// 删除Cookie中的列表集合
function deleteListFromCookie(name) {
    document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

// 修改Cookie中的列表集合
function updateListInCookie(name, list) {
    deleteListFromCookie(name);
    addListToCookie(name, list);
}

// 查询Cookie中的列表集合
function searchListInCookie(name, value) {
    var list = getListFromCookie(name);
    var result = [];
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === value) {
            result.push(list[i]);
        }
    }
    return result;
}
function guid() {
    function S4() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }
    return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}