// Constants
const minRows = 6;


function togglePopup() {
    const popup = document.getElementById("logPopup");
    const openBtn = document.getElementById("openBtn");

    // Toggle the hidden class on the popup
    popup.classList.toggle("hidden");

    // Optional: Hide the "Open" button when the popup is already open
    if (popup.classList.contains("hidden")) {
        openBtn.classList.remove("hidden");
    } else {
        openBtn.classList.add("hidden");
        // Refresh logs when opening to ensure data is current
        loadLogs();
    }
}


function formatData(data) {
    // Format the date string
    const dateStr = new Date(data.timestamp * 1000).toLocaleString('en-GB', { hour12: false });
    // Prepare the new content
    return rowContent = `
        <td>${dateStr}</td>
        <td>${data.app}</td>
        <td>${data.message}</td>
    `;
}

/**
 * Fetches logs from the API and populates the log table.
 */
async function loadLogs() {
  try {
    const response = await fetch('api/logs');
    const data = await response.json();
    const table = document.getElementById('logTable');

    // Clear existing rows efficiently
    table.innerHTML = '';
    // The total number of rows will be whichever is higher: 6 or your data count
    const rowsToRender = Math.max(minRows, data.length);

    for (let i = 0; i < rowsToRender; i++) {
        const row = document.createElement('tr');

        if (i < data.length) {
            // Fill row with real data
            row.innerHTML = formatData(data[i]);
        }
         else {
            // Fill row with "ghost" empty cells
            row.innerHTML = `<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>`;
        }

        table.appendChild(row);
    }
    // 2. Add empty "ghost" rows if we have fewer than 6 entries
//    if (data.length < minRows) {
//        for (let i = 0; i < (minRows - data.length); i++) {
//            const emptyRow = `<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>`;
//            table.innerHTML += emptyRow;
//        }
//    }
  } catch (error) {
    console.error('Error loading logs:', error);
  }
}

// Option 2 WebSockets for realtime data
const socket = new WebSocket("ws://localhost:8080/logging-webapp/ws/logs");

socket.onmessage = function (event) {
    const log = JSON.parse(event.data);
    const tableBody = document.getElementById("logTable");


    const rowContent = formatData(log);
    // Find the first "empty" row (contains &nbsp; or is whitespace only)
    const rows = tableBody.getElementsByTagName("tr");
    let emptyRow = null;
    if(rows.length <= minRows) {
        for (let i = 0; i < rows.length; i++) {
                // Check the first cell to see if it's a placeholder
                if (rows[i].cells[0].innerHTML === "&nbsp;" || rows[i].cells[0].textContent.trim() === "") {
                    emptyRow = rows[i];
                    break;
                }
            }
    }

    if (emptyRow) {
        // Replace the content of the existing placeholder row
        emptyRow.innerHTML = rowContent;
    } else {
        // No empty rows left! Create and add a new row to the bottom
        const newRow = document.createElement("tr");
        newRow.innerHTML = rowContent;
        tableBody.appendChild(newRow);
    }
};

/**
 * Deletes all logs and refreshes the display.
 */
async function clearLogs() {
  try {
    await fetch('api/logs', { method: 'DELETE' });
    loadLogs();
  } catch (error) {
    console.error('Error clearing logs:', error);
  }
}

// --- Initialization ---

// Auto-refresh logs every 2 seconds
//setInterval(loadLogs, 2000);

// Initial load on page start
loadLogs();