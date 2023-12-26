Download ES version elasticsearch-7.17.6

Project UI pages & steps
1. List of medications => Home page
	1. Fetch medications from API
	2. Display all medications in HTML
	3. On click, navigate to clinics list page
2. List of clinics => Clinics list page
	1. Get current location
	2. Fetch clinics from API
	3. Display all clinics in HTML
	4. On click, navigate to clinics details page (passing governamentHealthCode)
3. Clinic details => Clinic details page
	1. Get current location
	2. Fetch clinics from API
	3. Get governamentHealthCode from URL
	4. Find the clinic from response with required governamentHealthCode
	5. Display clinic details
