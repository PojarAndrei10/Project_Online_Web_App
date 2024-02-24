const dateNow = new Date(); // Obtine data si ora curenta

const year = dateNow.getFullYear(); // Extrage anul din data curenta
const month = ("0" + (dateNow.getMonth() + 1)).slice(-2); // Extrage luna din data curenta si adauga un zero in fata daca luna este mai mica decat 10
const date = ("0" + dateNow.getDate()).slice(-2); // Extrage ziua din data curenta si adauga un zero in fata daca ziua este mai mica decat 10

// Seteaza valoarea minima a elementului cu id-ul "inputDate" la data curenta (an-luna-zi).
document.getElementById("inputDate").min = year + "-" + month + "-" + date;

// Seteaza valoarea maxima a elementului cu id-ul "inputDate" la sfarsitul lunii curente (an-luna-31).
document.getElementById("inputDate").max = year + "-" + month + "-" + 31;
