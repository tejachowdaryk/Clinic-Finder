async function loadMedications() {
  const url = 'http://localhost:9855/medications/all';
  const rawResponse = await fetch(url);
  const medicationPage = await rawResponse.json();

  let medicationsHtml = ``;
  for (const medication of medicationPage.content) {
    const medicationHtml = `
      <div class="medication" onclick="navigateToClinicsList()">
        <span>${medication.medicationName}</span>
      </div>
    `;
    medicationsHtml += medicationHtml;
  }

  const container = document.getElementById('medicationsContainer');
  container.innerHTML = medicationsHtml;
}

function navigateToClinicsList() {
  window.location.href = "http://localhost:5500/clinics-list.html";
}

loadMedications();
