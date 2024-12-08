

document.addEventListener('DOMContentLoaded', function() {
    // Akkor kezdjük el az eseményfigyelőt, amikor a DOM teljesen betöltődött
    findId();
});

function findId() {


    // Az eseményfigyelőt csak akkor adjuk hozzá, amikor a DOM teljesen betöltődött
    document.getElementById('codeForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Ne küldje el a formot

        var inputpersonId = document.getElementById('codeInput').value.trim();
        var resultDiv = document.getElementById('result');
        const mapElement = document.getElementById('map');
        resultDiv.innerHTML = ''; // Ürítsük ki az eredmény mezőt


        // Keresés a listában
        const record = identificationData.find(item => item.personId === inputpersonId);

        if (record) {
            let output = `<h3>Talált azonosító:</h3>
                          <p> ${record.personId}</p>
                          <p>Egyenleg: ${record.money}</p> <p>`;
            //Map megjelenítése
            mapElement.style.display = 'block';
            // Jegyek kiírása
            let ticketOutput = '<h4 style="margin-left: 0;">Belépők:</h4> <ul>';
            let hasTickets = false;

            // Jegyek listája
            const tickets = {
                "adultFellingTicket": "Felnőtt élmény belépő",
                "studentFellingTicket": "Diák élmény belépő",
                "feelingPensionerTicket": "Pensioner élmény belépő",
                "adultBeachTicket": "Felnőtt strand belépő",
                "studentBeachTicket": "Diák strand belépő",
                "pensionerBeachTicket": "Nyugdíjas strand belépő",
                "adultThermalTicket": "Felnőtt termál belépő",
                "studentThermalTicket": "Diák termál belépő",
                "pensionerThermalTicket": "Nyugdíjas termél belépő",
                "aquaParkTicket": "Aqua Park belépő",
                "premiumTicket": "Prémium belépő"
            };
            // Jegyek megjelenítése
            for (const [ticket, name] of Object.entries(tickets)) {
                if (record[ticket] > 0) {
                    ticketOutput += `<li>${name},</li>`;
                    hasTickets = true;
                }
            }
            ticketOutput += '</ul>';
            if (!hasTickets) {
                ticketOutput = '<h4>Nem rendelkezik jeggyel!</h4>';
            }

            // Szolgáltatások kiírása
            let serviceOutput = '<h4 style="margin-left: 0;">Szolgáltatások:</h4> <ul>';
            let hasServices = false;

            // Szolgáltatások listája
            const services = {
                "sauna": "Szauna",
                "safeDeposit": "Széf",
                "lounger": "Napágy",
                "sunBed": "Napozóágy",
                "sunBedAtTheBeach": "Napozóágy a tengerparton",
                "baldachin": "Baldachin"
            };

            // Szolgáltatások megjelenítése
            for (const [service, name] of Object.entries(services)) {
                if (record[service] > 0) {
                    serviceOutput += `<li>${name}: ${record[service]}</li>`;
                    hasServices = true;
                }
                serviceOutput += '</ul>';
            }
            serviceOutput += '</ul> </p>';
            if (!hasServices) {
                serviceOutput = '<h4>Nem rendelkezik szolgáltatással!</h4>';
            }
         /*   // Szekrény megjelenítése
            const cupboardRecord = cupboardData.find(item => item.personId === inputpersonId);
            let cupboardOutput = '';
            if (cupboardRecord) {
                cupboardOutput = `<h4 style="margin-left: 0;">Szekrény száma:</h4>
                                  <p style="margin-left: 20px;">${cupboardRecord.cupboardNumber}</p>`;
            }*/

            // Összegzés
            resultDiv.innerHTML = output + ticketOutput + serviceOutput;//+cupboardOutput;
        } else {
            resultDiv.innerHTML = '<p>Nem található adat az adott azonosítóval!</p>';
            //Map rejtve marad
            mapElement.style.display = 'none';
        }
    });
}
