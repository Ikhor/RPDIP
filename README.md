
# Remote Parallel Digital Image Process

# SOFTWARE MODELING

This section describes the model on which the software was developed, divided into two subsections, client and server. In this way, each part of the modeling can be better detailed.

# Server Modeling

![](https://d2mxuefqeaa7sj.cloudfront.net/s_037C369A33F24C227CA22208365D4AFFB446CA165FC7B9028A0BA01F3413B3A8_1528728418518_file.png)


* Server.Filter.Local Package

In this package, the filter classes were grouped into two groups, border, border and neighborhood, and contrast. As it is a local approach, everyone followed the standard algorithm.

* Server.Filter.Thread Package

In this package, the filter classes were grouped in the same way as in the Server.Filter.Local package. However, a different approach to image processing is used, because a thread vector has been implemented to use the threads. This, in turn, receives a code for the desired filter and then, through polymorphism, the thread vector becomes a vector of the type of filter to be used, since the processing of the filters is now performed with a class child of the Thread class.

* Package Server.Filter.OpenMP

In this package, the filter classes were grouped in the same way as in the Server.Filter.Local package. The approach used was to create a class to load the DLL through a static block and declaration of native methods. For each class that implements some filter it was necessary to create component vectors to be passed to the JNI, for execution of native code written in C / C ++ by the Java platform.

* Server.Core Package

![](https://d2mxuefqeaa7sj.cloudfront.net/s_037C369A33F24C227CA22208365D4AFFB446CA165FC7B9028A0BA01F3413B3A8_1528728418522_file.png)

his package contains the classes and interfaces essential for the operation of the image processing on the server. The project has three classes and two interfaces.
The first interface to be addressed remoteFilters, this interface is necessary due to the use of RMI, since we must define in advance which are the remote processing methods to be implemented on the server. In this interface, we have three types of calls: the filter call, in which the remote filter is invoked that the client wants to use, the processing model, where the client determines what kind of processing he wants (which can be Local, Thread or Open MP). And finally (if it is Thread), the amount of thread pool you want.
The second interface deals with the required methods if you want to implement a new filter. In this way it is necessary to implement two functions: Apply and getBoundaries, the second being an abstract method. Therefore, if you want to develop a new filter, you should follow the following format:
public abstract ArrayList <ImageComponent> apply (ImageComponent image);
Sending as an parameter an image and returning an image vector. While the method, getBoundaries, is used if it is necessary to limit some processing in the image, for example in gamma filters, when we want to limit the multiplicative factor of the filter.
Among the classes, the first to be approached is the imageComponent, in which we have the name and title of the image, which will be used to demonstrate which filter and in which image was processed, a data to keep the history of the operations performed in that image until the moment, and finally, a bufferedImage image to be processed on the server, for this reason receives the transient attribute, for this class also it is necessary to use the interface serializable, due to the operation of marshling and unmarshling, necessary for the RMI perform the exchange of non-primitive data information,
The second class to be approached is the Boundary class, which is used for the restrictions in the image, as previously commented.

Finally, the third class is the server that uses all the filters developed for the application through the implementation of the interface remoteFilter, previously commented, and using the inheritance of the object UnicastRemoteObject. The main advantage of this method is to be able to update the methods on the server while the client does not need to update for error maintenance, since the interface will not be modified.

# Client Modeling

![](https://d2mxuefqeaa7sj.cloudfront.net/s_037C369A33F24C227CA22208365D4AFFB446CA165FC7B9028A0BA01F3413B3A8_1528728418526_file.png)

The client modeling is only divided into UserInterface classes. This package consists of five classes: GUI, ImageComponent, ImagePanel, and SimpleShower.
The ImageComponent class is for the client-to-server transmission interface. Therefore, there are no differences between the classes implemented on the server and the client.
Already the other classes refer to the user interface will be presented besides the operation of the class its proposed User Interface. The first class to be addressed is the ImagePanel class. This class is used to make a container for images defining, therefore, the region occupied by the Image in JFrame using the native Graphics class of Java.
The second class to be addressed is the SimpleShower class. This is the viewer of the selected filters. SimpleShower, in addition to demonstrating the result, used in the image title the requested operation, and if it is processed in Thread mode, will have the prefix "T:" before the name of the applied filter. In addition, it has an internal menu, accessible through the right click of the mouse, offering three options: Save, Transfer and History. In the first option, Save, the user can save the image obtained by using the filter. In the second option, Transfer, the user can transfer the image from SimpleShower to the GUI, so you can use another filter that you also want. Finally, in the third option, History, the client can view the filters that have been applied to the image so far.

Finally, the last class is the GUI, in it we have 4 menus, File, Filters, Window and Help.
The help menu has only one about the project. The window menu allows the user to close all SimpleShower or use the F4 key to perform the same processing as the GUI's prioritization of the Simpleshower. In the Filter menu, the implemented filters divided by category are located, finally in the File menu, it is possible to open and save images and change the operating mode for the image processing, as well as to choose the amount of Threads desired for the processing of the images.

![](https://d2mxuefqeaa7sj.cloudfront.net/s_037C369A33F24C227CA22208365D4AFFB446CA165FC7B9028A0BA01F3413B3A8_1528728419060_file.png)


