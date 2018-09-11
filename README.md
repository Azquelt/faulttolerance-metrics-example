This is a basic sample application which demonstrates the metrics produced by MicroProfile Fault Tolerance 1.1

## Instructions

Modify `src/main/liberty/config/server.xml` to set an appropriate admin password.

Run `./gradlew libertyStart` - this will download openliberty, build the app, deploy it and start the server.

When it's running, visit http://localhost:9080 in your web browser. You should see a page listing some services which each demonstrate a different Fault Tolerance annotation.

After accessing one or more of these services, visit https://localhost:9443/metrics and you should see metrics produced for the method that implements that service.

When you're finished, run `./gradlew libertyStop` to stop the server.
