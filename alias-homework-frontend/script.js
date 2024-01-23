const sendButton = document.querySelector("#get-cat-image");

sendButton.addEventListener("click", (e) => {
    e.preventDefault();

    let imageType = document.querySelector('input[name="image-type"]:checked') !== null ? document.querySelector('input[name="image-type"]:checked').value : "";
    let imageFilter = document.querySelector('input[name="image-filter"]:checked') !== null ? document.querySelector('input[name="image-filter"]:checked').value : "";
    let imageWidth = document.querySelector('input[name="image-width"]').value;
    let imageHeight = document.querySelector('input[name="image-height"]').value;

    let url = buildUrl(imageType, imageFilter, imageWidth, imageHeight);
    fetchCatPicture(url);
})

function buildUrl(imageType, imageFilter, imageWidth, imageHeight){
    let baseUrl = "https://cataas.com/cat?";

    if (imageType.length > 0){
        baseUrl += "type=" + imageType + "&";
    }
    
    if (imageFilter.length > 0){
        baseUrl += "filter=" + imageFilter + "&";
    }

    if (imageWidth.length > 0){
        baseUrl += "width=" + imageWidth + "&";
    }

    if (imageHeight.length > 0){
        baseUrl += "height=" + imageHeight;
    }

    if (baseUrl.charAt(baseUrl.length - 1) === "&"){
        baseUrl = baseUrl.substring(0, baseUrl.length - 1)
    }

    return baseUrl;
}

async function fetchCatPicture(url){
    const image = document.createElement('img');
    const imageDiv = document.querySelector("#image-div");
    const response = await fetch(url);

    return await response.blob().then((blob) => {
        const objectURL = URL.createObjectURL(blob);
        image.src = objectURL;
        if (imageDiv.childNodes.length !== 0){
            imageDiv.childNodes[0].src = objectURL
        } else {
            imageDiv.appendChild(image);
        }
    });
}