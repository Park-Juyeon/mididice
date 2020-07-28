# Mididice 
[![Build Status](https://travis-ci.org/mididice/mididice.svg?branch=master)](https://travis-ci.org/mididice/mididice)

Role the dice and play generated music!

## Getting Started


### Prerequisites

* [jfugue](http://www.jfugue.org/) - music programming for java
* [timidity](https://sourceforge.net/projects/timidity/) -  MIDI to WAVE converter
* [MIDI.js](http://www.midijs.net/) - JavaScript MIDI Player using W3C Web Audio
* [waveform.js](https://wavesurfer-js.org/) - audio waveform visualization

### Installing

the step will be on Ubuntu 16.04

```

git clone https://github.com/mididice/mididice
cd mididice
apt install openjdk-8-jdk
apt install maven
apt install timidity
apt install lame

```

### Start

```
mvn package
java -jar target/mididice-0.0.1.jar
```

## Built With

* [spring-boot](http://spring.io/projects/spring-boot) - spring based application
* [maven](https://maven.apache.org/) - project management

## Screenshot

![alt text](https://github.com/mididice/mididice/blob/master/main.PNG)

## Started From

[midiyapp](https://github.com/nine-hundred/midiyapp) - origin project
