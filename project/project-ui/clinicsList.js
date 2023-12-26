async function loadClinics() {
  navigator.geolocation.getCurrentPosition(function(result) {
    const latitude = result.coords.latitude;
    const longitude = result.coords.longitude;

    loadClinicsWithLatLon(latitude, longitude);
  });
}

async function loadClinicsWithLatLon(latitude, longitude) {
  const url = `http://localhost:9855/clinics/search?latitude=${latitude}&longitude=${longitude}`;
  const rawResponse = await fetch(url);
  const clinics = await rawResponse.json();

  let clinicsHtml = ``;
  for (const clinic of clinics) {
    const clinicHtml = `
      <div class="clinic" onClick="navigateToDetails('${clinic.governmentHealthCode}')">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">${clinic.name}</h5>
              <p class="card-text">Hours: ${clinic.hours}</p>
              <p class="card-text">Phone: ${clinic.telephone}</p>
              <p class="card-text">Govt. Code: ${clinic.governmentHealthCode}</p>
            </div>
          </div>
        </div>
    `;

    clinicsHtml += clinicHtml;
  }

  const container = document.getElementById('clinicsContainer');
  container.innerHTML = clinicsHtml;
}

function navigateToDetails(governmentHealthCode) {
  window.location.href = `http://localhost:5500/clinic-details.html?governmentHealthCode=${governmentHealthCode}`;
}

loadClinics();
