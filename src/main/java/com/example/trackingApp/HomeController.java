package com.example.trackingApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
            <html>
            <head>
                <title>Tracking Number Generator</title>
                <script>
                    let cachedResponse = null; // Store the fetched response here

                    async function generateTrackingNumber() {
                        if (cachedResponse) {
                            // If we already have a response, just show it again without fetching
                            document.getElementById('result').innerText = JSON.stringify(cachedResponse, null, 2);
                            return;
                        }

                        const origin = document.getElementById('origin').value;
                        const destination = document.getElementById('destination').value;
                        const weight = document.getElementById('weight').value;
                        const createdAt = document.getElementById('createdAt').value;
                        const customerId = crypto.randomUUID();
                        const customerName = document.getElementById('customerName').value;
                        const customerSlug = document.getElementById('customerSlug').value;

                        const url = `/next-tracking-number?origin_country_id=${origin}&destination_country_id=${destination}&weight=${weight}&created_at=${encodeURIComponent(createdAt)}&customer_id=${customerId}&customer_name=${encodeURIComponent(customerName)}&customer_slug=${encodeURIComponent(customerSlug)}`;

                        try {
                            const response = await fetch(url);
                            const data = await response.json();
                            cachedResponse = data; // Save response in cache
                            document.getElementById('result').innerText = JSON.stringify(data, null, 2);
                        } catch (error) {
                            document.getElementById('result').innerText = "Error fetching tracking number.";
                        }
                    }
                </script>
            </head>
            <body>
                <h2>Generate Tracking Number</h2>
                <label>Origin Country ID: <input id="origin" value="CA"/></label><br/>
                <label>Destination Country ID: <input id="destination" value="FR"/></label><br/>
                <label>Weight: <input id="weight" value="3.75"/></label><br/>
                <label>Created At (ISO format): <input id="createdAt" value="2025-06-01T09:30:00Z"/></label><br/>
                <label>Customer Name: <input id="customerName" value="Alice Smith"/></label><br/>
                <label>Customer Slug: <input id="customerSlug" value="alice-smith"/></label><br/><br/>
                <button onclick="generateTrackingNumber()">Generate</button>

                <h3>Response:</h3>
                <pre id="result" style="background:#f0f0f0; padding:10px;"></pre>
            </body>
            </html>
        """;
    }
}
