let users = [];
        let currentPage = 0;
        const pageSize = 10;

        const clientId = 'mqttjs_' + Math.random().toString(16).substr(2, 8);
        const host = 'wss://broker.mqtthq.com:8083/mqtt';

        const options = {
            keepalive: 60,
            clientId: clientId,
            protocolId: 'MQTT',
            protocolVersion: 4,
            clean: true,
            reconnectPeriod: 1000,
            connectTimeout: 30 * 1000,
            will: {
                topic: 'chat/topic',
                payload: 'Connection Closed abnormally..!',
                qos: 0,
                retain: false
            },
            rejectUnauthorized: false
        };

        console.log('Connecting to MQTT broker...');
        const client = mqtt.connect(host, options);

        client.on('error', (err) => {
            console.log('Connection error: ', err);
            alert('MQTT connection failed!');
            client.end();
        });

        client.on('reconnect', () => {
            console.log('Reconnecting...');
        });

        client.on('connect', () => {
            console.log('Connected to MQTT broker');
            client.subscribe('chat/topic', { qos: 0 });
        });

        client.on('message', (topic, message) => {
            console.log('Received message:', topic, message.toString());
            displayMessage(JSON.parse(message.toString()));
        });

        function displayMessage(message) {
            const messageArea = document.getElementById('messageArea');
            const messageElement = document.createElement('div');
            messageElement.textContent = message.content;
            messageArea.appendChild(messageElement);
            saveMessage(message);
        }

        function sendMessage() {
            const messageInput = document.getElementById('messageInput').value;
            const message = {
                content: messageInput,
                timestamp: new Date().toISOString()
            };
            client.publish('chat/topic', JSON.stringify(message), { qos: 0, retain: false });
            displayMessage(message); // Display the message immediately
            document.getElementById('messageInput').value = '';
        }

        function saveMessage(message) {
            let messages = JSON.parse(sessionStorage.getItem('chatMessages')) || [];
            messages.push(message);
            sessionStorage.setItem('chatMessages', JSON.stringify(messages));
        }

        function loadMessages() {
            let messages = JSON.parse(sessionStorage.getItem('chatMessages')) || [];
            messages.forEach(displayMessage);
        }

        async function fetchUsers(page) {
            try {
                const response = await fetch(`/api/users/paged?page=${page}&size=${pageSize}`);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                users = data.content;
                displayUsers(users);
                document.getElementById('currentPage').textContent = page + 1;
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        }

        function displayUsers(userList) {
            const userListElement = document.getElementById('userList');
            userListElement.innerHTML = userList.map(user => `<li>${user.username}</li>`).join('');
        }

        function searchUsers() {
            const searchQuery = document.getElementById('searchBar').value.toLowerCase();
            const filteredUsers = users.filter(user => user.username.toLowerCase().includes(searchQuery));
            displayUsers(filteredUsers);
        }

        function sortUsers(order) {
            const sortedUsers = users.sort((a, b) => {
                if (order === 'asc') return a.username.localeCompare(b.username);
                else return b.username.localeCompare(a.username);
            });
            displayUsers(sortedUsers);
        }

        function prevPage() {
            if (currentPage > 0) {
                currentPage--;
                fetchUsers(currentPage);
            }
        }

        function nextPage() {
            currentPage++;
            fetchUsers(currentPage);
        }

        window.onload = () => {
            fetchUsers(currentPage);
            loadMessages();
        };