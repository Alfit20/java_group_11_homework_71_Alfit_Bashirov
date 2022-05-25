async function handleClick(e) {
    e.preventDefault();
    const form = document.getElementById("cart_form");
    const data = new FormData(form)
    const options = {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: data
    };
    await fetch("http://localhost:8080/cart/" + e.target.id, options);
}

const buttons = document.querySelectorAll('.cart');
buttons.forEach(btn => {
    btn.addEventListener('click', handleClick)
})