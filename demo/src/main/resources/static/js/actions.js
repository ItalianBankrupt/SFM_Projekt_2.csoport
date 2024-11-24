console.log("actions.js betöltve!");

document.addEventListener('DOMContentLoaded', function() {
    // Akkor kezdjük el az eseményfigyelőt, amikor a DOM teljesen betöltődött
    findId();
});

function findId() {
    console.log("Lekérdezés indult...");

    // Az eseményfigyelőt csak akkor adjuk hozzá, amikor a DOM teljesen betöltődött
    document.getElementById('codeForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Ne küldje el a formot

        var personId = document.getElementById('codeInput').value.trim();
        var resultDiv = document.getElementById('result');
        resultDiv.innerHTML = ''; // Ürítsük ki az eredmény mezőt

        console.log("personId:", personId); // Ellenőrizzük, hogy helyesen jön-e be az ID
        console.log("identificationData:", identificationData); // Ellenőrizzük, hogy mi van a listában

        // Keresés a listában
        const record = identificationData.find(item => item.personId === personId);

        if (record) {
            resultDiv.innerHTML = `
                <h3>Talált rekord:</h3>
                <p>Person ID: ${record.personId}</p>
                <p>Money: ${record.money}</p>
            `;
        } else {
            resultDiv.innerHTML = '<p>Nem található adat az adott azonosítóval!</p>';
        }
    });
}
