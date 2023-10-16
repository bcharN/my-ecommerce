const getProducts = () => {
    return fetch("/api/products")
        .then((response) => response.json())
        .catch((error) => console.log(error))
};

const getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then((response) => response.json())
}

const handleAddToCart = (productId) => {
    return fetch(`/api/cart/${productId}`, {
        method: 'POST'
    });
};

const refreshCurrentOffer = async () => {
    const offer = await getCurrentOffer();
    const offerEl = document.querySelector('.offer');

    offerEl.querySelector('.total').textContent = `${offer.total} PLN`;
    offerEl.querySelector('.itemsCount').textContent = `${offer.productsCount} items`;

}

const createHtmlElFromString = (template) => {
    let parent = document.createElement("div");
    parent.innerHTML = template.trim();

    return parent.firstChild;
}

const createProductComponent = (product) => {
    const template = `
        <li class="product">
            <span class="product__description">${product.name}</span>
            <div class="product__image-container">
                <img class="product__image" src="${product.picture}"/>
            </div>
            <span class="product__price">${product.price}</span>
            <button
                class="product__add-to-cart"
                data-product-id="${product.id}"
            >
                Add to cart
            </button>
        </li>
    `;

    return createHtmlElFromString(template);
}

const initializeAddToCartHandler = (el) => {
    el.addEventListener('click', (e) => {
        let button = e.target;
        const productId = button.getAttribute('data-product-id');

        handleAddToCart(productId)
            .then(() => refreshCurrentOffer())
            .catch((error) => console.log(error))
        ;
    });

    return el;
}


const initializeEcommerce = async () => {
    await refreshCurrentOffer();

    const productsList = document.querySelector('#productsList');
    const products = await getProducts();
    products
        .map(p => createProductComponent(p))
        .map(productEl => initializeAddToCartHandler(productEl))
        .forEach(productEl => {
            productsList.appendChild(productEl)
        });

}


(() => {
    console.log("My ebook store works");
    initializeEcommerce()
        .then(r => {});
})();










//const a = 5;
//const foo = () => {console.log("hello world!!")};
//const getProducts = () => {
//    return fetch("/api/products")
//        .then(response => response.json());
//}
//const getCurrentOffer = () => {
//    return fetch("/api/offer")
//        .then(response => response.json());
//}
//const refreshOffer = async () => {
//    const offer = await getCurrentOffer();
//    const cart = document.querySelector('.cart');
//    cart.querySelector('.total').textContent = `${offer.total} PLN`;
//    cart.querySelector('.itemsCount').textContent = `${offer.itemsCount} items`;
//}
//const createHtmlFromString = (htmlAsString) => {
//    const tmpElem = document.createElement('div');
//    tmpElem.innerHTML = htmlAsString.trim();
//    return tmpElem.firstChild;
//}
//const createHtmlComponent = (product) => {
//    const template = `
//        <li class="product">
//            <h4>${product.name}</h4>
//            <img />
//            <span>${product.price}</span>
//            <button
//                class="product__add-to-cart"
//                data-product-id="${product.id}"
//            >
//                Add to cart +
//            </button>
//        </li>
//    `;
//    return createHtmlFromString(template);
//}
//const addToCart = (productId) => {
//    return fetch(`/api/add-to-cart/${productId}`, {
//        method: 'POST'
//    });
//};
//const initializeAddToCartHandler = (htmlEl) => {
//    const btn = htmlEl.querySelector('button.product__add-to-cart');
//    btn.addEventListener('click', () => {
//        addToCart(btn.getAttribute('data-product-id'))
//            .then(refreshOffer());
//    });
//    return htmlEl;
//};
//(async () => {
//    const productsListEl = document.querySelector('#products-list');
//    await refreshOffer();
//    const products = await getProducts();
//    products
//        .map(product => createHtmlComponent(product))
//        .map(productComponent => initializeAddToCartHandler(productComponent))
//        .forEach(el => productsListEl.appendChild(el));
//})();
//












/*
const getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json());
}

const createHtmlFromString = (htmlAsString) => {
    const tmpEle = document.createElement('div');
    tmpEle.innerHTML = htmlAsString.trim();
    return tmpEle.firstChild;
}

const createHtmlComponent = (product) => {
const template =
'
<li class="product">
<h4>${product.name}</h4>
<img />
<span<${product.price}</span>
<button class="product_add-to-cart" data-product-id="${product.id}">Add to cart</button>
</li>
'
}

(()=>{
    const ProductsListE1 = document.querySelector('#products-list');
    getProducts().then(products => { products

    .map(product => createHtmlComponent(product))
    .map(PrductComponent)
    })
})
const getCurrentOffer = ()=>{
return fetch("/api/offer")
.then(response=>response.json())
}

/*
const refreshOffer = async ()=>
{
const offer = await getCurrentOffer();
const cart = document.querySelector('.cart');

cart.querySelector('.total').textContent = '${offer.total}' PLN
}
*/