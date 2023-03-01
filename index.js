const btn = document.getElementById('change_btn')

function changeTitle(){
    document.getElementById('header').textContent = "HI"
    console.log("tiknul i che")
}
console.log(btn.className)
btn.addEventListener('click', changeTitle)
