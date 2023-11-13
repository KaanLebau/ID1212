import java.io.*;
public class PageHandler extends ExceptionHandler{

    String welcomePage = "<!-- index.html -->\n" +
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "  <title>Welcome to Guessing Game!</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Welcome to Guessing Game!</h1>\n" +
            "<form id=\"dataForm\">\n" +
            "  <input type=\"text\" id=\"textInput\" placeholder=\"Enter the number\">\n" +
            "  <input type=\"submit\" value=\"Is it?\">\n" +
            "    <!-- Additional Data Placeholder -->\n" +
            "</form>\n" +
            "\n" +
            "<script>\n" +
            "        document.getElementById('dataForm').addEventListener('submit', function (event) {\n" +
            "            event.preventDefault();\n" +
            "            var inputValue = document.getElementById('textInput').value;\n" +
            "\n" +
            "            // Send a POST request with the entered text to the server\n" +
            "            fetch('/', {\n" +
            "                method: 'POST',\n" +
            "                body: inputValue,\n" +
            "                headers: {\n" +
            "                    'Content-Type': 'text/plain'\n" +
            "                }\n" +
            "            });\n" +
            "        });\n" +
            "    </script>\n" +
            "</body>\n" +
            "</html>\n";
    public PageHandler(){

        //writeWelcomeContentToIndex();
    }


    public void writeWelcomeContentToIndex() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("index.html"))) {
            // Write the content of the welcome page to index.html
            //writer.write(welcomePage);
            System.out.println("Welcome page content has been written to index.html");
        } catch (IOException e) {
            fileHandler(e, "Page handler");
        }
    }

    /**
     * This method informs the client about wrong type input
     */
    public void errMsg(){}

    /**
     * This method writes over the index.html file with current game status
     *
     * @param result [High, Success, Low]
     * @param numberOfAttempts number of attemts
     */
    public void handlePageUpdate(String result, int numberOfAttempts) {
        try {
            String filename = "index.html";
            File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            String additionalData ="";
            if(result != "Success"){
                additionalData = "<p>it is : " + result + "</p>"
                        + "<p>Number of Attempts: " + numberOfAttempts + "</p>";
            }else{
                additionalData = "<p>it is : " + result + "</p>"
                        + "<p>Number of Attempts: " + numberOfAttempts + "</p>";
            }
            String updatedContent = content.toString().replace("<!-- Additional Data Placeholder -->", additionalData);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(updatedContent);
            writer.close();

            System.out.println("index.html file has been updated with additional game data.");
        } catch (IOException e) {
            outHandler(e, "Page Handler");
        }
    }
}
