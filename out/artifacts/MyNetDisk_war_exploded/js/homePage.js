

function but() {
    var fileDownload = document.getElementById("fileDownload");

    var fileAll = fileDownload.value;

    var fileAddress = fileAll.substr(0,fileAll.indexOf(","));
    var fileName = fileAll.substring(fileAll.indexOf(",") + 1);

    window.location.href = "http://localhost:8081/file.do?method=download&address=" + fileAddress + "&name=" + fileName;
}

function delfile() {
    var deleteButton = document.getElementById("deleteButton");

    var fileAll = deleteButton.value;

    var fileAddress = fileAll.substr(0,fileAll.indexOf(","));
    var fileName = fileAll.substring(fileAll.indexOf(",") + 1);

    window.location.href = "http://localhost:8081/file.do?method=deleteFile&address=" + fileAddress + "&name=" + fileName;
}