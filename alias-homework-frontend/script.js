const sendButton = document.querySelector("#get-cat-image");

sendButton.addEventListener("click", (e) => {
    e.preventDefault();

    let imageType = document.querySelector('input[name="image-type"]:checked') !== null ? 
                    document.querySelector('input[name="image-type"]:checked').value : "medium";
    let imageFilter = document.querySelector('input[name="image-filter"]:checked') !== null ?
                    document.querySelector('input[name="image-filter"]:checked').value : "";

    let imageWidth = document.querySelector('input[name="image-width"]').value;
    let imageHeight = document.querySelector('input[name="image-height"]').value;
    
    if (imageHeight.length === 0){
        console.log("Empty height")
    }
    if (imageWidth.length === 0){
        console.log("Empty width")
    }

    console.log(imageHeight.length);
})

function buildUrl(){
    let baseUrl = "https://cataas.com/cat/"
}