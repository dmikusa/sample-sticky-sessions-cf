# Cloud Foundry Sticky Session Demo

This is a sample application which can be used to play around with the sticky session support built into Cloud Foundry.

## Usage

### With Sticky Sessions

To use this app, run the following:

- `./mvnw package`
- `cf push`

This will deploy with the default `manifest.yml`, which deploys the basic test. Access the assigned route.

### Sticky Session Details

What's happening is that when you deploy, you'll end up with two application instances. Request to the application are split across the two instances evenly. Because sticky session support works out-of-the-box though, you'll end up with requests to the app only hitting one instance. As you refresh the page, you'll see the counter increment but you won't see the the IP/instance GUID change. This is because your requests are stuck to that app instance.

To make requests potentially go to the other application instance, you need to click the "Reset Counter" link or delete your session cookie. This will be a new request, not already stuck to an instance and so it'll be round-robined to one of the two app instances. I say "potentially" go to the other application instance because it's round-robin behavior. You may need to repeat a couple times if multiple people are accessing the demo at the same time.

### Without Sticky Sessions

To use this app, run the following:

- `./mvnw package`
- `cf push -f manifest-no-sticky.yml`

This will deploy with the `manifest-no-sticky.yml` file, which deploys the same code but configures a non-standard session cookie name (i.e. not `JSESSIONID`). This in turn breaks sticky session support in CF, which requires the session cookie to be named `JSESSIONID`.

### Without Sticky Session details

What's happening is that when you deploy, you'll end up with two application instances. Request to the application are split across the two instances evenly. Because sticky session has been disabled, you'll end up with requests to the app being round-robined across the two instances. As you refresh the page, you won't see the counter increment and you'll see the IP/app instance guid change on every request. This is because the request are not stuck to one app instance. This in turn breaks the counter, because session state by default is stored in memory.

This demo does not cover storing session data in a persistent storage like Redis or Gemfire, but if you want the counter to work correctly and you want sticky sessions to be disabled then you'd need to configure this. Adding & configuring Spring Session support to the app would be the natural way to go. This is left as an exercise to the reader. See the [Samples & Guides here](https://docs.spring.io/spring-session/docs/2.4.1/reference/html5/#samples).
