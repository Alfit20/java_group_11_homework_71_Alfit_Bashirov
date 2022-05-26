// async function handleClick(e) {
//     e.preventDefault();
//     const form = document.getElementById("cart_form");
//     const data = new FormData(form)
//     console.log(data.get('quantity'))
//     const options = {
//         method: 'post',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: data
//     };
//     await fetch(`http://localhost:8080/cart/${e.target.id}`  + "?quantity=" + data.get('quantity'), options);
// }
//
// let input = document.querySelectorAll('#quantity')
// let count = 1;
// input.forEach(i => {
//     i.setAttribute('id', 'quantity_' + count)
//     count++
// })
//
// const buttons = document.querySelectorAll('.cart');
// buttons.forEach(btn => {
//     btn.addEventListener('click', handleClick)
// })











// let localCart = [];
//
// function findInCart(id) {
//     for (const item of localCart) {
//         if (item.id == id) {
//             return item;
//         }
//     }
//     return null;
// }
// let fd = new FormData()
// function pushCart() {
//     const list = {
//         cartItems: localCart
//     }
//
//     // fd.append('cartItems', localCart)
//     fd.append('json', JSON.stringify(list))
//     fetch(
//         'http://localhost:8080/cart',
//         {
//             method: "POST",
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: fd
//         }
//     )
// }
//
// const addToCart = event => {
//     const id = parseInt(event.target.id)
//     let item = findInCart(id)
//     if (!item) {
//         localCart.push({
//             id: id,
//             qty: 1
//         })
//     } else {
//         item.qty++
//     }
//     pushCart()
// };
//
// (function addToCartActions() {
//     let buttons = document.querySelectorAll('.cart')
//     for (let btn of buttons) {
//         btn.addEventListener('click', addToCart)
//     }
// })();
