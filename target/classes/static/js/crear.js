document.addEventListener("DOMContentLoaded", async function() {
    try{
        const respuesta = fetch("/localhost:8080/productos/crear");
        const datos = await response.json();
              
        const productoContainer = document.getElementById('formulario')
              data.forEach(producto => {
                   const productoElement = document.createElement('div')
                   productoElement.classList.add('producto')
                   chisteElement.innerText = chiste.texto
                   chistesContainer.appendChild(chisteElement)
              });
            
         })
         .catch(error => console.error('Error fetching chistes:', error))
})