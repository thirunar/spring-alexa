# Framework for developing Alexa Skill using Spring [In development]

Ever wondered that an Alexa skill can be created by just creating a JSON? Well, this library is a saviour for you.
This library can be used as library for developing Alexa skill in Java. The objective of developing this library is to make people life much easier while developing an Alexa skill. With this framework, an Alexa skill can be created within minutes

## Pre-requisities
- Java 8
- Spring
- Maven

## Get Started
### How to build the library?
- Clone the repository `git@github.com:thirunar/spring-alexa.git` 
- cd `spring-alexa`
- Run `mvn clean install`. This will install the library in the local maven repo
- Once installed, add the dependency in the skill which you are going to develop.
For maven:

```
		<dependency>
			<groupId>com.alexaframework</groupId>
			<artifactId>spring-alexa</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
```

For gradle:
```
dependencies {
    compile("com.alexaframework:spring-alexa:0.0.1-SNAPSHOT")
}
```

### Why use this framework?
- Provides boiler plate code for developing Alexa skill
- The adoption of Spring framework for developing Java application is growing massively. (Alexa Java Sdk)[https://github.com/amzn/alexa-skills-kit-java] is a plain Java application. This library helps in adding spring framework for developing the Alexa skill.
- Has handled all the default Intents
- A basic skill can be developed by just creating a JSON
- Well tested code

### How to use the library?
- Install the library as mentioned above
- Create a new spring boot application using the (spring initializer)[https://start.spring.io/].
- Add the dependency in `pom.xml` or `build.gradle`
- Create a file called `intent_metadata.json`. The json should have `intent name` and `response` for the intent. The intent are nothing but the invocations coming to Alexa. In this json, the intent name has to be specified and the corresponding speech response for the intent has to be given. For example,

```
[
  {
    "name": "HelloWorldIntent",
    "response": "Hello world. This is alexa"
  },
  {
    "name": "AMAZON.CancelIntent",
    "response": "Thank you for using the skill"
  }
]
```
When there is an invocation from Alexa, the library will first look into this json if there is anything defined in the file matching the intent which has came in. If there is a match, the library will return the speech response. 

