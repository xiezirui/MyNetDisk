window.onclick = function () {
    var click_button = document.getElementsByName("fileDownload")

    console.log(1)

    click_button.click = function (){
        alert(1)
    };
}