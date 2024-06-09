
const getCurrrentOffer = () => {
    return fetch("/api/current-offer")
        .then(response:Response => response.json());
}

const getProducts = () : Promise<any> => {
    return fetch("/api/products")
        .then(response:Response => response.json());
}

const addToCart = (productId):Promise<Response> => {
    return fetch("/api/products", {
        method: "POST"
    })
}

const acceptOffer = (acceptOfferRequest)

const createProductHtmlEl = (productData) : void => {
    const htmlEl:HTMLDivElement = document.createElement("div");
    const template:string = '
        <li>
            <h4>${productData.name}</h4>
            <span>${productData.price}</span>
            <img src="https:"/>
            <div>
                <button/>
            </div>
        </li>
        ';
        htmlEl.innerHTML = template.trim();
        return htmlEl.firstChild;
}

const initializeCartHandler = (productId) {
    productHtmlEl.querySelector("button")
}

document.addEventListener("DOMContentLoaded, ():void => {
    ")

(() => {
    getProducts()
        .then(productsAsJson => productsAsJson.map(createProductHtmlEl))
        .then(productsAsHtml)
        .then(result => console.log(result))
})();















const greet = (name) => {
    alert('Hello ${name}');
}

const logNames = async () => {
  const response = await fetch("/api/names");
  const names = await response.json();
  return names;
}

(() => {
const namesEl = document.querySelector("#names")
    logNames()
        .then(names => {
            namesEl.innerHTML = names.join(" | ");
        })
})();