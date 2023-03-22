// Fetch year data on page load
let xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
    if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
            const yearSelect = document.getElementById('year');
            const xmlDoc = xhr.responseXML;
            const yearNodes = xmlDoc.getElementsByTagName('year');
            for (let i = 0; i < yearNodes.length; i++) {
                const year = yearNodes[i].childNodes[0].nodeValue;
                const option = document.createElement('option');
                option.value = year;
                option.textContent = year;
                yearSelect.appendChild(option);
            }
        } else {
            console.log('Error: ' + xhr.status);
        }
    }
};
xhr.open('GET', 'http://judah.cedarville.edu/~gallaghd/ymm/ymmdb.php?fmt=xml', true);
xhr.send();

// Add event listener to year dropdown
const yearSelect = document.getElementById('year');
yearSelect.addEventListener('change', function() {
    const year = yearSelect.value;
    if (year) {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    const makeSelect = document.getElementById('make');
                    makeSelect.innerHTML = '<option value="">Select Make</option>';
                    const xmlDoc = xhr.responseXML;
                    const makeNodes = xmlDoc.getElementsByTagName('make');
                    for (let i = 0; i < makeNodes.length; i++) {
                        const make = makeNodes[i].textContent;
                        const option = document.createElement('option');
                        option.value = make;
                        option.textContent = make.substring(make.search(/[A-Z]/));
                        makeSelect.appendChild(option);
                    }
                } else {
                    console.log('Error: ' + xhr.status);
                }
            }
        };
        xhr.open('GET', '/~gallaghd/ymm/ymmdb.php?fmt=xml&year=' + year, true);
        xhr.send();
    } else {
        const makeSelect = document.getElementById('make');
        makeSelect.innerHTML = '<option value="">Select Make</option>';
        const modelSelect = document.getElementById('model');
        modelSelect.innerHTML = '<option value="">Select Model</option>';
    }
});

// Add event listener to make dropdown
const makeSelect = document.getElementById('make');
makeSelect.addEventListener('change', function() {
    const year = yearSelect.value;
    const make = makeSelect.value;
    if (make) {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    const modelSelect = document.getElementById('model');
                    modelSelect.innerHTML = '<option value="">Select Model</option>';
                    const xmlDoc = xhr.responseXML;
                    const modelNodes = xmlDoc.getElementsByTagName('model');
                    for (let i = 0; i < modelNodes.length; i++) {
                        const model = modelNodes[i].textContent;
                        const option = document.createElement('option');
                        option.value = model;
                        option.textContent = model.substring(model.search(/[A-Z]/));
                        modelSelect.appendChild(option);
                    }
                } else {
                    console.log('Error: ' + xhr.status);
                }
            }
        };
        xhr.open('GET', '/~gallaghd/ymm/ymmdb.php?fmt=xml&year=' + year + '&make=' + make, true);
        xhr.send();
    } else {
        const modelSelect = document.getElementById('model');
        modelSelect.innerHTML = '<option value="">Select Model</option>';
    }
});
