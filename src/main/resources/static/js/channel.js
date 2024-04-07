window.onload = function() {
    const channelId = document.getElementById('channel-id').value;
    setInterval(() => {
        fetchAndDisplayMessages(channelId);
    }, 500);
};

function fetchAndDisplayMessages(channelId) {
    fetch(`/channel/${channelId}/messages`)
        .then(response => response.json())
        .then(data => {
            let messagesContainer = document.getElementById('messages-container');
            messagesContainer.innerHTML = "";
            data.forEach(message => {
                const p = document.createElement("p");
                p.textContent = `${message.user.username}: ${message.content}`;
                messagesContainer.appendChild(p);
            });
        })
        .catch(error => console.error('Error fetching messages:', error));
}

document.getElementById("message-form").addEventListener("submit", handleFormSubmission);

function handleFormSubmission(event) {
    event.preventDefault();
    const content = document.querySelector(`input[name="content"]`).value;
    const channelId = document.getElementById("channel-id").value;
    const user = JSON.parse(sessionStorage.getItem("user"));

    if (content.trim() !== '') {
        const message = {
            user: user,
            content: content,
            channelId: channelId
        };

        fetch('/channel/' + channelId, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(message),
        })
            .then(response => {
                if (response.ok) {
                    document.querySelector('input[name="content"]').value = '';
                } else {
                    console.error('Failed to post message');
                }
            })
            .catch(error => {
                console.error('Error posting message:', error);
            });
    }
}
