# Simple Spark api
This little app exists to test the Spark framework and demonstrate how to expose some routes to enable gets and
posts.

## Option 1: Installing via IntelliJ
This code was tested in IntelliJ on a Mac, to get it working:

- Install the latest version of Java
- Download json-simple-1.1.1.jar (https://code.google.com/p/json-simple/)
- Copy to /Library/Java/Extensions/json-simple-1.1.1.jar
- Add to CLASSPATH (this step might not be necessary)
```
export CLASSPATH=$CLASSPATH:/Library/Java/Extensions/json-simple-1.1.1.jar)
```
- In IntelliJ, right click project and select “Open Module Settings (F4)"
- Select the "dependencies" tab on the right
- Click + button at the bottom of the dependencies window
- Select “1 JARs or Directories”
- Navigate to folder and select json-simple-1.1.1.jar
- Run the server
```
Right-click on SparkAPI.java and select "run SparkAPI.main()"
```

## Option 2: Installing via homebrew
To install via homebrew:

- Install homebrew:
```
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
- Use homebrew to install Gradle
```
brew install gradle
```
- ...and Cask:
```
brew install caskroom/cask/brew-cask
```
- Use cask to install Java:
```
brew cask install java
```
- Clone the repo
```
git clone git@github.com:LlamaComedian/gradle-test.git
```
- Compile it
```
cd gradle-test
gradle build
```
- Run it
```
gradle runJar
```

# Testing the routes
With the server running, you can hit the GET routes either in a browser or with a curl. The routes are:

- (GET) http://localhost:9090/hello - A simple test to return "hello world"
- (GET) http://localhost:9090/method - A route to demonstrate running functionality from a method
- (GET) http://localhost:9090/method/[anything] - A route to show how to add variables to routes
- (POST) http://localhost:9090/postit - A route to receive a simple text post, test with:
```
curl -X POST -d "hello world" http://localhost:9090/postit
```
- (POST) http://localhost:9090/postJSON - A route to receive a JSON object and parse it, test with:
```
curl -X POST -d '{"data" : [{"line" : "This is a line"}, {"line" : "This is also a line"}]}' http://localhost:9090/postJSON
```
