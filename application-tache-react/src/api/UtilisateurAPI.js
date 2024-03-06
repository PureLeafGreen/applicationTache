export default function addUser(user) {

    fetch('http://localhost:8080/api/utilisateur/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(r => r.json()).then(r => console.log(r)).catch(e => console.log(e));


}

