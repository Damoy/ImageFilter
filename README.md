# Image filter

<h2><b>Version 0.3 - Launch configuration</h2></b>

<h3>You can now provide command arguments in order to configure application behavior.</h3>
<h4>Available arguments</h4>

| Command name      | Command value example                      | Definition                           |
| ----------------- | -----------------------------------------  |------------------------------------- |
| finput            | resources/input/cube.png                   | Image file / folder to process       |
| fpalette          | _auto                                      | Palettes file or folder to use       |
| fgen              | resources/palettes/palettes_10random_4to16 | Palettes generation file output      |
| pgen              | 10                                         | Number of palettes to generate       |
| cgenMin           | 4                                          | Minimum colors number of one palette |
| cgenMax           | 16                                         | Maximum colors number of one palette |

<b>Complete command example:</b><br>

<i><b>java -jar ImageFilter.jar -finput resources/input/cube.png -fpalette _auto -pgen 10 -cgenMin 4 -cgenMax 16 -fgen resources/palettes/palettes_10_random_4to16.if</i></b>

Above command will make the application to:
<ul>
    <li>generate 10 color palettes</li>
        <ul>
            <li>each palette with a minimum of 4 colors</li>
            <li>each palette with a maximum of 16 colors</li>
        </ul>
    <li>use "palettes_10_random_4to16.if" file as palettes generation output file</li>
    <li>process "cube.png" image using generated palettes only</li>
</ul>

<p align="center">
    <img src="resources/examples/cube_random_4-16colors.gif" width="250"/>
</p>

---------------------------------------

<h2><b>Version 0.2 - Palette plugin</h2></b>

<h3>Adding your own colors palette</h3>
<ul>
    <li>[Option] | Create your own palette file at "./resources/palettes/".</li>
    <li>Add your own palette definition (follow instructions at "./resources/palettes/palettes_examples.if") on your desired palette file.</li>
</ul>

<p align="center">
    <img src="resources/examples/ectoplasma_random_256-512colors.gif" width="450"/>
</p>

---------------------------------------

<h2><b>Version 0.1</h2></b>

<h3>Available palettes</h3>
<ul>
    <li>Custom 5 colors.</li>
    <li>Custom 8 colors.</li>
    <li>Custom 16 colors.</li>
</ul>

Source image and processed images [source-p5-p8-p16].<br>
<p float="left">
    <img src="resources/examples/sourceInputExample.jpg" width="175"/>
    <img src="resources/examples/processed5colorsExample.jpg" width="175"/>
    <img src="resources/examples/processed8colorsExample.jpg" width="175"/>
    <img src="resources/examples/processed16colorsExample.jpg" width="175"/>
</p>

---------------------------------------

<h3>Custom 5 colors palette</h3>

| Filter color     | RGB value  |
| ------------- |:-------------:|
| Dark Burgundy | [79,49,48]    |
| Burgundy      | [80,140,215]  |
| Orange        | [170,80,66]   |
| Skin          | [216,189,138] |
| Yellow        | [216,215,143] |

---------------------------------------

<h3>Custom 8 colors palette</h3>

| Filter color     | RGB value  |
| ------------- |:-------------:|
| Black       | [0,0,0]         |
| Violet      | [85,65,95]      |
| Gray        | [100,105,100]   |
| Orange      | [215,115,85]    |
| Blue        | [80,140,215]    |
| Green       | [100,185,100]   |
| Yellow      | [230,200,110]   |
| White       | [220,245,255]   |

---------------------------------------

<h3>Custom 16 colors palette</h3>

| Filter color     | RGB value  |
| ------------- |:-------------:|
| Light Pink            | [232,204,210]   |
| Light Beige           | [222,214,202]   |
| Light LightGreen      | [203,211,187]   |
| Light DarkGreen       | [171,193,185]   |
| Light DarkBlue        | [156,171,177]   |
| Pink                  | [214,162,173]   |
| Beige                 | [195,181,159]   |
| LightGreen            | [160,175,132]   |
| DarkGreen             | [102,143,128]   |
| DarkBlue              | [74,102,112]    |
| Dark Pink             | [98,74,79]      |
| Dark Beige            | [89,83,73]      |
| Dark LightGreen       | [73,80,60]      |
| Dark DarkGreen        | [47,65,59]      |
| Dark DarkBlue         | [34,47,51]      |
| Gray                  | [100,105,100]   |

---------------------------------------

<h2>[Try it out]</h2>
    1. Put the desired images in "/resources/input/".<br>
    2. Launch by double clicking on ImageFilter.jar.<br>
3. Wait few seconds and enjoy processed images at "/resources/output" :)
