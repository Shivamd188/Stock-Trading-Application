function refreshPortfolio() {
    fetch('/api/portfolio')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#portfolio-table tbody');
            tableBody.innerHTML = ''; // Clear existing rows

            data.forEach(stock => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${stock.name}</td>
                    <td>${stock.quantity}</td>
                    <td>$${stock.price}</td>
                    <td>$${stock.totalValue}</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching portfolio:', error));
}

// Refresh the portfolio every 30 seconds
setInterval(refreshPortfolio, 30000);
