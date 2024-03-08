export function addUser(user) {

    fetch('http://localhost:8080/api/utilisateur/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(r => r.json()).then(r => console.log(r)).catch(e => console.log(e));


}

export function getUser(user) {
    fetch('http://localhost:8080/api/utilisateur/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(r => console.log(r)).then(r => console.log(r)).catch(e => console.log(e));
}

