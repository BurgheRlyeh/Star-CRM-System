const search_btn = document.getElementById('find_btn')

function log_data(event){
    event.preventDefault()
    const target = event.target
    const search_fields = target.parentNode

    console.log(search_fields.search_name.value)

}

search_btn.addEventListener('click', log_data)