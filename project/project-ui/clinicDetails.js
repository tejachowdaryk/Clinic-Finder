async function loadClinics() {
  navigator.geolocation.getCurrentPosition(function (result) {
    const latitude = result.coords.latitude;
    const longitude = result.coords.longitude;

    loadClinicsWithLatLon(latitude, longitude);
  });
}

async function loadClinicsWithLatLon(latitude, longitude) {
  const url = `http://localhost:9855/clinics/search?latitude=${latitude}&longitude=${longitude}`;
  const rawResponse = await fetch(url);
  const clinics = await rawResponse.json();

  const rqeuiredCode = window.location.href.split('governmentHealthCode=')[1];

  let clinicsHtml = ``;
  for (const clinic of clinics) {
    if (clinic.governmentHealthCode != rqeuiredCode) {
      continue;
    }

    const successIndicatorHtml = `<span class="text-success"><i class="bi bi-check-lg"></i></span>`;
    const failureIndicatorHtml = `<span class="text-danger">X</span>`;

    let icuIndicatorHtml = null;
    if (clinic.hasIcu) {
      icuIndicatorHtml = successIndicatorHtml;
    } else {
      icuIndicatorHtml = failureIndicatorHtml;
    }

    let ambiulanceIndicatorHtml = null;
    if (clinic.hasAmbulance) {
      ambiulanceIndicatorHtml = successIndicatorHtml;
    } else {
      ambiulanceIndicatorHtml = failureIndicatorHtml;
    }

    let wheelchairIndicatorHtml = null;
    if (clinic.hasWheelchairSupport) {
      wheelchairIndicatorHtml = successIndicatorHtml;
    } else {
      wheelchairIndicatorHtml = failureIndicatorHtml;
    }

    const clinicHtml = `
      <h1>${clinic.name}</h1>
      <p class="clinic-details-subtext">${clinic.governmentHealthCode}</h4>
      <p><i class="bi bi-clock"></i> ${clinic.hours}</p>
      <p><i class="bi bi-telephone"></i> ${clinic.telephone}</p>
      <p><i class="bi bi-phone"></i> ${clinic.mobile}</p>
      <div class="indicators-section">
        <p>ICU: ${icuIndicatorHtml}</p>
        <p><i class="bi bi-truck"></i> ${ambiulanceIndicatorHtml}</p>
        <p><i class="bi bi-person-wheelchair"></i> ${wheelchairIndicatorHtml}</p>
      </div>
      <a href="${clinic.webAddress}" target="_blank">Webpage</a>
    `;

    clinicsHtml += clinicHtml;
  }

  const container = document.getElementById('clinicDetails');
  container.innerHTML = clinicsHtml;
}

function navigateToDetails(governmentHealthCode) {
  window.location.href = `http://localhost:5500/clinic-details.html?governmentHealthCode=${governmentHealthCode}`;
}

loadClinics();
