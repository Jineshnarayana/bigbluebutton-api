This library allows to integrate a java application with a BigblueButton server.

It includes a Store of Objects that can produce Proxies for any of three different versions of BigBlueButton.

Legacy version:
It is any version before 0.80. This Proxy only implements the basic calls, although with all the parameters that any of the new versions can handle.

Administration
getCreateURL
getJoinURL
getEndURL

Monitoring
getVersionURL
getIsMeetingRunningURL
getMeetingsURL
getMeetingInfoURL

Version 0.80
Implements all the previous ones plus
getGetRecordingsURL
getPublishRecordingsURL
getDeleteRecordingsURL

Version 0.81 (TO DO)
Implements all previous ones plus:
getSetConfigXMLURL
getGetDefaultConfigXMLURL


Roadmap
Static execution of some commands
Methods for pre upload presentations
Implement action objects to be used as part a command pattern implementation.
