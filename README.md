# UBER

A Spring boot project which can onboard cab drivers and let the customers use the service. The cab drivers come into the system landscape by registering. The fare calculation is purely dynamic and is based on both the time and the distance travelled.
The properties are kept configurable.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Java, Maven

```
Give examples
```

### Running the project

mvn spring-boot:run

### API's exposed
http://localhost:8090/uber/launchCab

{
	"cabType":"default",
	"licencseNumber":"hp-22-2399",
	"location":{
		"x":4,
		"y":4
	}
}


http://localhost:8090/uber/allocateRide

{
	"rider":{
		"name":"abhishek",
		"gender":"male"
		
	},
	"location":{
		"x":4,
		"y":2
	}
}
http://localhost:8090/uber/finishRide

{
	"cabId":1,
	"location":{
		"x":6,
		"y":2
	}
}

