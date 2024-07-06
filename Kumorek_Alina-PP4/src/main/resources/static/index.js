//Products
getProducts();
refreshCart();

async function getProducts() {
    let products = await fetch("/api/products");
    let productsTxt = await products.text();

    let productsArr = JSON.parse(productsTxt);

    let inner = "";

    for (var i = 0; i < productsArr.length; i++)  {
        inner += `<li>
                        <h4>${productsArr[i].name}</h4>
                        <p>${productsArr[i].price} PLN</p>
                        <img alt='object' src='/images/${productsArr[i].image}' height='200'><br>
                        <button type='button' class='addbutton' onclick='addToCart("${productsArr[i].id}")'>Add to Cart</button>
                    </li>`;
    }

    document.getElementsByClassName("products")[0].innerHTML = inner;
}

//Cart
async function refreshCart() {
    let cart = await fetch("/api/current-offer");
    let cartTxt = await cart.text();

    let cartArr = JSON.parse(cartTxt);

    let inner = "";

    if (cartArr.itemsCount == 0) {
        inner = "Your cart is empty.";
    } else {
        inner += `<table>
                    <tr>
                        <th>Product Name</th>
                        <th>Unit Price</th>
                        <th>Quantity</th>
                        <th>Remove Item</th>
                    </tr>`;

        for (var i = 0; i < cartArr.offerProducts.length; i++) {

            inner += `<tr>
                            <td>${cartArr.offerProducts[i].name}</td>
                            <td>${cartArr.offerProducts[i].unitPrice / 100} PLN</td>
                            <td>${cartArr.offerProducts[i].quantity}</td>
                            <td><button type='button' class='rembutton' onClick='removeFromCart("${cartArr.offerProducts[i].id}")'>Remove Item</button></td>
                        </tr>`;
        }

        inner += `</table><p>
                    <div class="itemsnum">
                        <h4>Number of Items</h4>
                        <p>${cartArr.itemsCount}</p>
                    </div>
                    <div class="total">
                        <h4>Total</h4>
                        <p>${cartArr.total} PLN</p>
                    </div>`;

    }

    document.getElementsByClassName("offer")[0].innerHTML = inner;
}

async function addToCart(productId) {

    await fetch("/api/add-product/" + productId, {
        method: "POST"
    });

    await refreshCart();
}

async function removeFromCart(productId) {

    await fetch("/api/remove-product/" + productId, {
        method: "POST"
    });

    await refreshCart();
}

//Checkout
async function acceptOffer() {

    fetch("/api/accept-offer", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: document.getElementById("email").value,
            phone: document.getElementById("phone").value,
            firstName: document.getElementById("fname").value,
            lastName: document.getElementById("lname").value,
        })
    })
        .then( async (reservationDetails) => {
            let reservationTxt = await reservationDetails.text();
            let reservationArr = JSON.parse(reservationTxt);

            window.open(reservationArr.paymentUrl, "_self");
        });
}





























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