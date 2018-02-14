# WirePlankton

Development of a small network traffic analyzer as part of module "AOT-Praktikum Intelligente Softwaresysteme (WT2017/18)" at Technical University of Berlin.

(Original project home: [https://gitlab.tubit.tu-berlin.de/lemannma/AOT-Praktikum](https://gitlab.tubit.tu-berlin.de/lemannma/AOT-Praktikum))
 
  
## Features

* capture network packets from specific device
* use filters and memory restrictions for capturing
* save/load captured packets
* export packet information as a CSV
* get statistics about capturing packets
 
 
## How to use

1. Make sure you have [Java JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven](https://maven.apache.org/download.cgi) installed on your system (you might also need to [update your PATH](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html)).

2. To build the project with Maven navigate to project directory and run:
```
mvn clean package
```

3. In order to get skins applied, copy "styles" folder into the "target" directory.

4. Run **WirePlankton-xxx-jar-with-dependencies.jar** from "target" directory:
```
java -jar WirePlankton-xxx-jar-with-dependencies.jar
```

5. Enjoy!
 
 
## Built With

* [Pcap4J](https://github.com/kaitoy/pcap4j) - Java library for capturing, crafting and sending packets
* [JavaFX](http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html) - GUI framework
* [FindBugs](http://findbugs.sourceforge.net/) - Static code analysis tool
* [GitLab](https://about.gitlab.com/) - Git repository manager, wiki, issue tracking and CI/CD
* [Nexus](https://www.sonatype.com/nexus-repository-sonatype) - Software repository
* [Maven](https://maven.apache.org/) - Build automation tool and dependency management
 
 
## Authors

* **Anton Rudacov** - *Back end, CI* - [@antonrud](https://github.com/antonrud)
* **Stefan Pawlowski** - *Front end, GUI* - [@Stefuniverse](https://github.com/Stefuniverse)
* **Matthias Lehmann** - *Testing, QA* - [@lemannma](https://gitlab.tubit.tu-berlin.de/lemannma)
* **Svetlana Lepikhine** - *Documentation, Usability* - [@lepikhine](https://gitlab.tubit.tu-berlin.de/lepikhine)
 
 
## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
