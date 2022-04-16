function but() {
    var fileDownload = document.getElementById("fileDownload");

    var fileAll = fileDownload.value;

    var fileAddress = fileAll.substr(0,fileAll.indexOf(","));
    var fileName = fileAll.substring(fileAll.indexOf(",") + 1);

    console.log(fileName)
    console.log(fileAddress)

    window.location.href = "http://localhost:8081/file.do?method=download&address=" + fileAddress + "&name=" + fileName;
}