const queryInput = document.getElementById("query");
const formElement = document.getElementById("newsForm");

const loading = document.getElementById("loading");
const result = document.getElementById("result");

function sendQuery(event) {
        event.preventDefault();

        loading.style.display = "block";
        queryInput.value = "";
//        coś z przyciskiem

        fetch("http://localhost:8080/api/scrap/get-by-title", {
            method:"POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify({query: queryInput.value})
        })
        .then(res => res.json())
        .then(data => {
            result.innerText = JSON.stringify(data,null);
        })
        .catch(error => {
            console.error("Błąd: ", error)
        })
        .finally(() => {
            loading.style.display = "none";
//            coś z przyciskiem
        })

}

formElement.addEventListener("submit", sendQuery);