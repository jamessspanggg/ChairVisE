- [Final Report](#final-report---CS3219-GROUP-07)
- [ChairVisE 2.0](#chairvise-20)
  - [Existing Features](#existing-features)
    - [Google Login](#google-login)
    - [Upload Conference Data](#upload-conference-data)
    - [Create Presentations](#create-presentations)
    - [Data Visualizations](#data-visualizations)
    - [Download Presentations](#download-presentations)
    - [Share Presentations](#share-presentations)
  - [Designs](#designs)
    - [General Workflow](#general-workflow)
    - [Overall Architecture](#overall-architecture)
    - [Sequence Diagrams](#sequence-diagrams)
    - [Back-end Architecture](#back-end-architecture)
    - [Front-end Architecture](#front-end-architecture)
  - [Requirements](#requirements)
    - [Functional Requirements](#functional-requirements)
      - [User stories](#user-stories)
      - [Use cases](#use-cases)
        - [UC1 - Upload CSV files](#uc1---upload-csv-files)
        - [UC2 - Adding visualization](#uc2---adding-visualization)
        - [UC3 - Download presentation](#uc3---download-presentation)
        - [UC4 - Share presentation](#uc4---share-presentation)
        - [UC5 - Basic edit visualization](#uc5---basic-edit-visualization)
        - [UC6 - Advanced edit visualization](#uc6---advanced-edit-visualization)
        - [UC7 - Login with Google](#uc7---login-with-google)
    - [Non-Function Requirements](#non-function-requirements)
      - [Constraints](#constraints)
      - [Interface Requirements](#interface-requirements)
      - [Performance Requirement](#performance-requirement)
      - [Quality Attributes](#quality-attributes)
- [ChairVisE 3.0](#chairvise-30)
  - [Requirements](#requirements-1)
    - [Functional Requirements](#functional-requirements-1)
      - [User stories](#user-stories-1)
      - [Use Cases](#use-cases)
        - [UC 1 - Upload multiple files of the same type](#uc-1---upload-multiple-files-of-the-same-type)
        - [UC 2 - View uploaded files](#uc-2---view-uploaded-files)
        - [UC 3 - Delete uploaded files](#uc-3---delete-uploaded-files)
        - [UC 4 - Copy chart to other presentation](#uc-4---copy-chart-to-other-presentation)
        - [UC 5 - Preview of file contents on upload](#uc-5---preview-of-file-contents-on-upload)
        - [UC 6 - Select file for relevant visualization](#uc-6---select-file-for-relevant-visualization)
        - [UC 7 - Choose chart representation for a visualization](#uc-7---choose-chart-representation-for-a-visualization)
        - [UC 8 - Upload multiple files at a go](#uc-8---upload-multiple-files-at-a-go)
        - [UC 9 - Switch to dark mode](#uc-9---switch-to-dark-mode)
      - [Functional Requirements](#functional-requirements-2)
      - [User Stories | Use Cases | Functional Requirements mappings](#user-stories--use-cases--functional-requirements-mappings)
    - [Non Functional Requirements](#non-functional-requirements)
      - [Constraints](#constraints-1)
      - [Interface Requirements](#interface-requirements-1)
      - [Performance Requirement](#performance-requirement-1)
      - [Quality Attributes](#quality-attributes-1)
  - [Development Process](#development-process)
  - [Enhancements](#enhancements)
    - [1. Make existing visualisations more meaningful to the user](#1-make-existing-visualisations-more-meaningful-to-the-user)
    - [2. Add more visualisations to provide more value to the user](#2-add-more-visualisations-to-provide-more-value-to-the-user)
    - [3. Persistence of multiple conference data](#3-persistence-of-multiple-conference-data)
    - [4. User interface improvements](#4-user-interface-improvements)
    - [5. Co-authorship Queries](#5-co-authorship-queries)
    - [6. Others](#6-others)
  - [Design](#design)
    - [Design Decisions](#design-decisions)
      - [MVC Pattern for Front-End](#mvc-pattern-for-front-end)
      - [Predefined Queries and Analysis Logic](#predefined-queries-and-analysis-logic)
      - [[US1] New user file table](#us1-new-user-file-table)
      - [[US6] Storage of file ids for charts](#us6-storage-of-file-ids-for-charts)
      - [[US11] Email Notification](#us11-email-notification)
    - [ER Diagrams](#er-diagrams)
  - [Future Developments](#future-developments)
    - [1. Enhancement to [US2] & [US3]](#1-enhancement-to-us2--us3)
    - [2. Enhancement to [US6]](#2-enhancement-to-us6)
    - [3. Rigidity of predefinedQueries.js in Frontend codebase](#3-rigidity-of-predefinedqueriesjs-in-frontend-codebase)
    - [4. Presentation Mode](#4-presentation-mode)
  - [Glossary](#glossary)



# Final Report - CS3219 GROUP 07


| Team member        | Roles           | Subtasks  |
| ------------- |:-------------:| -----:|
| James Pang Mun Wai <br>A0169761R   | Full Stack Software Engineer | US3<br>US5<br>US7<br>US9<br>US10<br>US16 |
| Khoo Jin Zheng Daniel <br>A0177406B  | Full Stack Software Engineer      |   US4<br>US8<br>US15 |
| Koh Chi Hao <br> A0168472W | Team Lead <br> Full Stack Software Engineer      |    US1<br>US11<br>US13<br>US14 |
| Tan Jin Ying <br> A0168492R| Full Stack Software Engineer    |  US2<br>US6 |


# ChairVisE 2.0 

<p align="center"> 
<img src="https://i.imgur.com/FjMDetS.png"/> 
<br>
Figure 1. ChairVisE Home Page
</p>

ChairVisE is designed to enable **conference program chairpersons** to visualize and share conference submission statistics. By parsing the information in different formats, ChairVisE aims to assist users to obtain the most value out of the information uploaded. ChairVisE also supports sharing and exporting of such visualization.


## Existing Features

### Google Login
Users can log in securely with their existing Google account which reduces the burden of remembering password. 

<p align="center"> 
<img src="https://i.imgur.com/7bJ2OAd.png" width="350px"/> 
<br>
Figure 2. Navbar with login button
</p>

<p align="center"> 
<img src="https://i.imgur.com/L8JFIXI.png" width="350px"/> 
<br>
Figure 3. Selection of Google Account
</p>
 
### Upload Conference Data
ChairVisE empowers users with the ability to upload metadata of conferences to the applications in CSV format.  

Users are given the choice of uploading of 2 different formats of conference data, namely `SoftConf` and `EasyChair`.   

<p align="center"> 
<img src="https://i.imgur.com/4qbyJW1.png" width="350px"/> 
<br>
Figure 4. Import Data Format Type Account
</p>

With selecting either of the formats, users are further able to choose up to 3 different table types of datasets to be uploaded, being, `Author Record`, `Review Record` and `Submission Record`.  

<p align="center"> 
<img src="https://i.imgur.com/PJUxdcb.png" width="350px"/> 
<br>
Figure 5. Import Data Table Type
</p>

<br/>

After deciding on the table types, users would be able to select whether their files have headers and whether they would like to use the predefined mapping within the application.

<br/>

<p align="center"> 
<img src="https://i.imgur.com/lX0FYTf.png" width="350px"/> 
<br>
Figure 6. Import Data Other Options Account
</p>

<br/>

After selecting all the options above, users would be able to upload their CSV files. 

<br/>

<p align="center"> 
<img src="https://i.imgur.com/mJRSX1H.png" width="350px"/> 
<br>
Figure 7. Import Data Upload CSV File
</p>

<br/>

### Create Presentations
Users can create presentations by adding the visualizations created.

### Data Visualizations
Users can visualize 3 types of records that they have uploaded into the system. The types of records are `Author Record`, `Review Record` and `Submission Record` and their data can be visualized in many ways.

Here is the list of visualizations available concerning the type of record uploaded: 

| Author Record | Review Record | Submission Record |
| -------- | -------- | -------- |
| Submission Rank Author | Word Cloud for Reviewer Comment | Word Cloud for All Submission Keywords |
| Submission Rank Country | Review Weighted Score Distribution | Word Cloud for Accepted Submissions Keywords |
| Submission Rank Organization | Review Weighted Evaluation Score Statistic Summary | Word Cloud for Rejected Submissions Keywords |
| | Reviewer Expertise Level Statistic Summary | Word Cloud for All Full Papers Submissions Keywords |
| | Review Confidence Level Statistic Summary | Word Cloud for All Posters and Demos Submissions Keywords |
| | Recommendation for Best Paper Distribution | Word Cloud for All Short Papers Submissions Keywords |
| | Review Count Summary for Each Submission | Word Cloud for All Workshop Submissions Keywords |
| | Reviewer Assignment Rank | Submission Rank Paper Author in Full Papers |
| | Reviewer Average Expertise Level Rank | Submission Acceptance Rate Rank Paper Author |
| | Reviewer Average Confidence Level Rank | Submission Accepted Rank Paper Author |
| | Reviewer Average Evaluation Score Rank | Submission Rank Track |
| | Number of Review Distribution | Acceptance Ratio Track |
| | Reviewer Average Expertise Level Distribution | Acceptance Ratio by Year |
| | Reviewer Average Confidence Level Distribution | |
| | Reviewer Average Evaluation Score Distribution | |
| | Average Expert Level For Submission | |
| | Average Confidence Level For Submission | |
| | Reviewer Confidence vs Score | | 

Records are also combined to generate more meaningful visualizations:

| Author Record + Submission Record | Review Record + Author Record | Submission Record + Review Record |
| -------- | -------- | -------- |
| Submission Rank Author in Full Papers | Average Weighted Score Rank Author | Acceptance Rate and Weighted Score |
| Submission Acceptance Rate Rank Author | Average Weighted Score Rank Organization | Average Weighted Score By Track |
| Submission Acceptance Rate Author Distribution | Average Weighted Score Rank Organization  | Earliest Review in Days For Submission |
| Submission Accepted Rank Author |  | Average Weighted Score Rank Paper Author |
| Submission Acceptance Rate Rank Organization | | |
| Submission Accepted Rank Organization | | |
| Submission Acceptance Rate Rank Organization | | |
| Submission Accepted Rank Country | | |

The visualization can be represented by different types of charts, including `Pie Chart`, `Bar Chart`, `Line Chart` as well as `Word Cloud`.

Example of `Pie Chart`:
![Pie Chart Example](https://i.imgur.com/v8596KR.png)
<p align="center">Figure 8. Submission Rank Country Pie Chart</p>
<br>

Example of `Bar Chart`:
![Bar Chart Example](https://i.imgur.com/wAww8Qu.png)
<p align="center">Figure 9. Submission Rank Author Bar Chart</p>
<br>

Example of `Line Chart`:
![Line Chart Example](https://i.imgur.com/L1JGrFc.png)
<p align="center">Figure 10. Acceptance Ratio by Year Line Chart</p>
<br>

Example of `Word Cloud`:
![Word Cloud Example](https://i.imgur.com/8eFgmnk.png)
<p align="center">Figure 11. Word Cloud for All Submissions</p>
<br>

### Download Presentations
Users can download their presentations as PDF for offline viewing.

![Downloading Presentation](https://i.imgur.com/ZnnIMNZ.png)
<p align="center">Figure 12. Download Presentation UI</p>

### Share Presentations
The presentations created can be shared via email or shareable link to other users. Permissions to view or edit the shared presentation will be given to the users.

![Sharing Presentation](https://i.imgur.com/4WzguE5.png)
<p align="center">Figure 13. Share Presentation UI</p>

## Designs
### General Workflow

<p align="center"> 
<img src="https://i.imgur.com/nFbRybS.png"/>
<br>
Figure 14. General workflow of ChairVisE 2.0
</p>

The diagram above shows a general workflow of a user of ChairVisE application. 

### Overall Architecture
<p align="center"> 
<img src="https://i.imgur.com/Aeg5tON.png"/> 
<br>
Figure 15. Overall Architecture of ChairVisE 2.0
</p>

The overall architecture is designed with an N-tier design pattern. Single Responsibility Principle (SRP) is applied everywhere. Each layer/classes has its responsibility. The Model-View design pattern is applied to the frontend.

**Backend:**
- **UI**: The component consists of API controllers and WebPage controllers. API controllers are responsible for handling API calls by the frontend. WebPage controllers are responsible for serving static production Vue files.
- **Logic**: The main logic of the application is in Java using the Spring framework.
- **Storage**: The storage layer of the application uses the persistence framework provided by Google App Engine, using MySQL 5.6.
- **Common**: The Common component contains utility code (data transfer objects, helper classes, etc.) used across the application.

**Frontend:**
- **Models**: The models are stored in a centralised store provided by Vuex.
- **View**: The views are component-based stored in a `.vue` file.

    
### Sequence Diagrams

#### Uploading of CSV
<p align="center"> 
<img src="https://i.imgur.com/0sMZ1Nn.png"/> 
<br>
Figure 16. Sequence diagram of Uploading Author CSV File
</p>

The above sequence diagram describes the interactions between components when uploading an `author` csv file to ChairVisE.

### Back-end Architecture
![Back-end Architecture](https://i.imgur.com/jUF2s8H.png)
<p align="center">Figure 17. Back-end Architecture</p>
<br>

The diagram above illustrates the backend package overview of the existing system.

**UI Component**
The UI component is the first stop for all requests received by the backend of the web application.
- ui.controller.api: Provides backend Representational State Transfer (REST) API access to the users.
- ui.controller.data: Contains helper objects to be sent to the client in JSON.
- ui.controller.webpage: Handles static file requests for the users.
- ui.advice: Handles exception thrown by the application.

**Logic Component**
The Logic component handles the business logic. In particular, it is responsible for:
- Managing CRUD operations, ensuring the integrity of data.
- Providing a mechanism for checking access control rights.

**Storage Component**
The Storage component performs create, read, update and delete (CRUD) operations on data entities individually. It contains minimal logic beyond what is directly relevant to CRUD operations.

**Common Component**
The Common component contains common utilities used across the web application.
- common.util: Contains utility classes.
- common.exceptions: Contains custom exceptions.
- common.datatransfer: Contains data transfer objects (DTOs).
- common.entity: Contains entity stored in the database.

### Front-end Architecture
<p align="center"> 
<img src="https://i.imgur.com/zf2C5aJ.png"/>
<br>
Figure 18. Front-End Architecture
</p>

The package mainly consists of:
- common: Contains utility functions and the constants that are shared among other packages
- store: Contains the core logic of the application. It serves as a centralized store for all the other components
- components: Contains reusable UIs and display Logic components that are called by multiple pages
- views: Contains main UI pages that may use the reusable UIs on top of the page

#### UI Views

##### Home
![Home View](https://i.imgur.com/dsDLgtm.png)
<p align="center">Figure 19. Home View</p>

#### Analyze
![Analyze View](https://i.imgur.com/r8p6dvP.jpg)
<p align="center">Figure 20. Analyze View</p>


#### Import Data 
![Import Data View](https://i.imgur.com/Mt2nKMU.png)
<p align="center">Figure 21. Import Data View</p>

## Requirements
### Functional Requirements
#### User stories 

| No. | User story | 
| -------- | -------- | 
| US1 |  As a user, I want to login via Google so that I do not need to remember any password. |
| US2 | As a user, I want to upload a `.csv` file and view different chart visualizations so that I can understand trends within the data.|
| US3 | As a user, I want to export my visualizations as a PDF, so that I can view it offline. |
| US4 | As a user, I want to share my visualizations with other users so that they can view it using a link. |
| US5 | As a user, I want other users to be able to edit my visualizations so that we can collaborate. |
| US6 | As a user, I want to create multiple visualizations so that I can create different reports. |
| US7 | As an advanced user, I want to be able to select the different type of columns in my data and display it so that I can customize my visualizations. |
| US8 | As a user, I want to edit my charts so that I can customize my visualization titles and descriptions. |

#### Use cases 

##### UC1 - Upload CSV files
###### Main Success Scenario
1. User requests to import data
2. ChairVisE shows the relevant fields to the user
3. User selects the format type of the file they will be uploading
4. User selects the table type of the file they will be uploading
5. User selects if their files have header and if they would want a predefined mapping from ChairVisE
6. ChairVisE checks if all the 4 fields have been filled up
7. ChairVisE shows the user the upload file section
8. User proceeds to upload their CSV file
9. ChairVisE redirects user and shows the mapping of the uploaded file
10. User validates their mapping
11. User selects to upload with their CSV with the relevant mappings
12. ChairVisE ask for confirmation from the user
13. User confirms the upload
14. ChairVisE stores the uploaded CSV file
Use case ends.
###### Extensions
- 8a. User uploads a non-CSV file
    - 8a1. Import Data page becomes unresponsive
Use case ends.

- 10a. User maps their columns manually
Use case resumes from step 11.


##### UC2 - Adding visualization
###### Precondition
- User has created a presentation
###### Main Success Scenario
1. User adds visualization from CSV file that is uploaded
2. The visualization is shown in the presentation
Use case ends. 
###### Extensions
- 1a. User adds visualization from CSV file that is not uploaded
    - 1a1. An empty visualization is shown in the presentation
    - Use case ends.

##### UC3 - Download presentation
###### Precondition
- User has created a presentation
###### Main Success Scenario
1. User selects presentation to be downloaded
2. User downloads presentation as a PDF file

##### UC4 - Share presentation 
###### Precondition
- User has created a presentation 
###### Main Success Scenario
1. User selects presentation to share
2. CharvisE generates a URL for user
Use case ends.

###### Extensions
- 2a. User changes the permission of URL to view only
Use case ends.
- 2b. User changes the permission of URL to edit and view
Use case ends.
- 2c. User changes the permission of URL to cannot access
Use case ends.
- 2d. User adds an email and select view only
Use case ends. 
- 2e. User adds an email and select edit and view
Use case ends.

##### UC5 - Basic edit visualization
###### Precondition
- User has created a presentation and a chart
###### Main Success Scenario
1. User selects the chart to edit 
2. ChairVisE displays the basic edit options to the user
3. User edits the relevant information
4. User saves the edit
5. CharVisE display updated information
Use case ends.
###### Extensions
- 3a. User previews the edits
Use case ends.

##### UC6 - Advanced edit visualization
###### Precondition
- User has created a presentation and a chart
###### Main Success Scenario
1. User selects the chart to edit 
2. ChairVisE displays the basic edit options to the user
3. User toggles to have the advanced edit option
4. ChairVisE displays the advanced edit option to the user
5. User edits the relevant information
6. User saves the edit
7. CharVisE display updated information
Use case ends.
###### Extensions
- 5a. User previews the edits
Use case ends.

##### UC7 - Login with Google
###### Precondition
User did not login to the website before

###### Main Success Scenario 
1. User login with their Google account
2. CharVisE redirects to home page
Use case ends.

### Non-Function Requirements

#### Constraints
The application has the following constraints:

| Type | Constraints | 
| -------- | -------- | 
| Data | Only accepts `.csv` extension files |
| Design | - Only allow one of each table type file to be uploaded. <br>  - Only can export file as PDF. <br> - Only can use predefined mapping when a file is uploaded. <br> - Only allow metadata files of `author`, `review`, `submission`. <br> |
| Implementation | The front-end of the application is developed using Vue.js and chart.js, while the backend is developed by Spring Boot.|
| Physical | - Only works with an active internet connection. <br> - Only works with a modern browser. |
| Architecture | The website must be a Single Page App (SPA). |

#### Interface Requirements

##### User Interfaces
The GUI is built mainly using Vue.js and chart.js.

##### Software Interfaces
CharVisE imports data in the form of `.csv` extension files, and exports presentation in `PDF` format.

##### Hardware Interfaces
CharVisE can store the exported file into the hard disk. Access will be managed by the operating system.

#### Performance Requirement
- CharVisE should upload `.csv` file within 10 seconds. 
- CharVisE should export presentation in `PDF` within 5 seconds. 

#### Quality Attributes

##### Extensibility
Able to extend to support other file formats such as PDF in the future.

##### Maintainability
CharVisE frontend is built with MVC pattern and backend is built with N-tier architectural style.

##### Portability
Supports modern browsers such as Chrome, Safari and Mozilla Firefox and Microsoft Edge. Should also support main-stream Operating Systems such as Windows, MacOS and Linux.

##### Reliability
The data uploaded will be the same as the data shown in the visualization.

##### Reusability
At least 50% of the existing classes can be re-used in other data-visualisation applications.

##### Security
Logging into ChairVisE is through Google OAuth, which provides highly reliable security.

##### Serviceability
CharVisE only works over an active internet connection.

# ChairVisE 3.0
## Requirements
### Functional Requirements 

#### User stories

**Priorities**: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| No. | User story | Priority | 
| -------- | -------- | -------- | 
| US1 | As a user, I want to be able to upload multiple files of the same type, so that I can also have the option to view the visualizations that I have created from previously uploaded data files. | * * * |
| US2 | As a user, I want to be able to view the files that I have uploaded, so that I manage the files I have uploaded. | * * * | 
| US3 | As a user, I want to be able to delete the files that I have uploaded, so that I manage the files I have uploaded. | * * * |
| US4 | As a user, I want to be able to copy charts from one presentation to another, so that I won't have to re-create the existing charts again.  | * * * |
| US5 | As a user, I want to have a preview that I have uploaded, so that I can map each column correctly. | * * * |
| US6 | As a user, I want to be able to select the different table type (`author`, `review`, `submission`) that I have uploaded to the system so that I can know which visualization options that I can choose from. | * * * |
| US7 | As a user, I want to know what does the axis on my visualizations represents, so that I can have a better understanding. | * * * |
| US8 | As a user, I want to choose the different type of charts for a particular visualization, so that I can have cohesive understanding. | * * |
| US9 | As a user, I want to have more analysis options under my presentations, so that I can have more meaningful data. | * * |
| US10 | As a user, I want the advance edit to be understandable so that I can easily use the advance edit features. | * * |
| US11 | As a user, I want to receive an email notification when another shared with me their presentation. | * * |
| US12 | As a user, I want to upload multiple files at a go, so that I can reduce the time of uploading files. | * |
| US13 | As a user, I want the website to support dark mode so that I can protect myself from eye strain when using the website at night. | * |
| US14 |As a user, I want to be able to view visualisations on co-author data, so that I can analyse and interpret more on the co-author relationship. | ** |
| US15 |As a user, I want to have a standalone help page, so that I can easily know where to seek information from. | * |
| US16 | As a user, I would like to have a prompt before deleting an existing file, presentation or visualisations, so that I can minimise my deletion mistakes. | ** |

#### Use Cases

##### UC 1 - Upload multiple files of the same type
###### Main Success Scenario
1. User requests to import data
2. ChairVisE shows the relevant fields to the user
3. User selects the format type of the file they will be uploading
4. User selects the table type of the file they will be uploading
5. User selects if their files have header and if they would want a predefined mapping from ChairVisE
6. ChairVisE checks if all the 4 fields have been filled up
7. ChairVisE shows the user the upload file section
8. User proceeds to upload their CSV file
9. ChairVisE redirects user and shows the mapping of the uploaded file
10. User validates their mapping
11. User selects to upload with their CSV with the relevant mappings
12. ChairVisE ask for confirmation from the user
13. User confirms the upload
14. ChairVisE creates a new entry in the database for new file
15. ChairVisE stores the uploaded CSV file
Use case ends.
###### Extensions
- 10a. User maps their columns manually
Use case resumes from step 11.

##### UC 2 - View uploaded files
###### Main Success Scenario
1. User requests to view their upload files
2. ChairVisE displays all the uploaded files to the user
Use case ends. 

##### UC 3 - Delete uploaded files
###### Precondition
- User has uploaded some files
###### Main Success Scenario
1. User request to view their upload files
2. ChairVisE displays all the uploaded files to the user
3. User selects one of the files to delete
4. ChairVisE confirms with the user on the action
5. User confirms action
6. ChairVisE deletes the uploaded file
Use case ends.
###### Extensions
- 5a. User cancels actions
Use case ends. 

##### UC 4 - Copy chart to other presentation
###### Precondition
- User already has 2 existing presentation
- User has at least one chart on 1 of the 2 presentation
###### Main Success Scenario
1. User opens one of the existing presentations with at least one existing chart
2. User request to copy one of the charts
3. ChairVisE displays all existing presentation to the user
4. User selects one of the existing presentations
5. ChairVisE copies the chart with the corresponding chart setting to the selected presentation
6. ChairVisE redirects to the selected presentation page
Use case ends.
###### Extensions
- 4a. User cancel the export
Use case ends. 

##### UC 5 - Preview of file contents on upload
###### Precondition
- User is at the mapping page after uploading their csv

###### Main Success Scenario
1. ChairVisE display header and top 5 rows in csv file
Use case ends.

##### UC 6 - Select file for relevant visualization
###### Precondition
- User already has an existing presentations
###### Main Success Scenario
1. User selects one of their existing presentation
2. ChairVisE displays a selection area of the file types that the user has previously uploaded
3. User selects the different table types
4. ChairVisE displays the different filters options to the user
5. User selects one of the filter options
6. ChairVisE displays the chart for the relevant options
Use case ends.
###### Extensions
- 2a. User hasn't uploaded any files
    - 2a1. ChairVisE displays the relevant message to the user
Use case ends.

##### UC 7 - Choose chart representation for a visualization
###### Preconditions
- User already has an existing presentation with one existing visualizations within the presentation
###### Main Success Scenario
1. ChairVisE displays an option to allow the user to choose from different chart representations for an existing visualization
2. User selects another representation for the current visualization
3. ChairVisE updates the current visualization to the user-selected representation
Use case ends. 

##### UC 8 - Upload multiple files at a go
###### Main Success Scenario
1. User selects more than 1 file
2. User drag and drop the multiple files into ChairVisE
3. ChairVisE shows that multiple files have been uploaded
4. Continue with **UC 1** for each file
Use case ends.

##### UC 9 - Switch to dark mode
###### Main Success Scenario
1. User switches to dark mode
2. ChairVisE updates user interface to dark mode
Use case ends.

#### Functional Requirements
1. The user should be able to upload multiple files of the same type (author, review, submission)
2. The user should be able to view all the files they have previously uploaded
3. The user should be able to delete files they have previously uploaded
4. The user should be able to copy specified charts with the corresponding settings from one presentation to another presentation
5. The user should be able to preview a partial data of the CSV file they have uploaded on the mapping page
6. The user should be able to select the different table type (`author`, `review`, `submission`) of files uploaded to be visualized
7. The user should be able to see the axis labels on all the charts they have created 
8. For some of the user's visualization, the user should be able to change between different charts for their visualization
9. The user should receive an email when other users share their presentation with this particular user
10. The user should be able to change to a dark mode
11. The user should be able to view all help instructions in the help page
12. The user should get a prompt before deleting files, presentations and visualisations

#### User Stories | Use Cases | Functional Requirements mappings  

| User Stories | Use cases | Functional Requirements |
|:------------ | --------- | ----------------------- |
| US1          | UC1       | FR1                     |
| US2          | UC2       | FR2                     |
| US3          | UC3       | FR3                     |
| US4          | UC4       | FR4                     |
| US5          | UC5       | FR5                     |
| US6          | UC6       | FR6                     |
| US7          | -         | FR7                     |
| US8          | UC7       | FR8                     |
| US9          | -         | -                       |
| US10         | -         | -                       |
| US11         | -         | FR9                     |
| US12         | UC8       | -                      |
| US13         | UC9       | FR10                    |
| US15         | -         | FR11                    |
| US16         | -         | FR12                    |
### Non Functional Requirements 

#### Constraints
The application has the following constraints:

| Type | Constraints | 
| -------- | -------- | 
| Data | Only accepts `.csv` extension files |
| Design | - Only can export the file as PDF. <br> - Only can use predefined mapping when a file is uploaded. <br> - Only allow metadata files of `author`, `review`, `submission`. <br> |
| Implementation | The front-end of the application is developed using Vue.js and chart.js, while the backend is developed by Spring Boot.|
| Physical | - Only works with an active internet connection. <br> - Only works with a modern browser. |
| Architecture | The website must be a Single Page App (SPA). |

#### Interface Requirements

##### User Interfaces
The GUI is built mainly using Vue.js and chart.js.

##### Software Interfaces
CharVisE imports data in the form of `.csv` extension files, and exports presentation in `PDF` format.

##### Hardware Interfaces
CharVisE can store the exported file into the hard disk. Access will be managed by the operating system.

#### Performance Requirement
- CharVisE should upload `.csv` file within 10 seconds. 
- CharVisE should export presentation in `PDF` within 5 seconds. 

#### Quality Attributes

##### Extensibility
Able to extend to support other file formats such as PDF in the future.

##### Maintainability
CharVisE frontend is built with MVC pattern and backend is built with N-tier architectural style.

##### Portability
Supports modern browsers such as Chrome, Safari and Mozilla Firefox and Microsoft Edge. Should also support main-stream Operating Systems such as Windows, MacOS and Linux.

##### Reliability
The data uploaded will be the same as the data shown in the visualization. 

##### Reusability
At least 50% of the existing classes can be re-used in other data-visualisation applications.

##### Security
Logging into ChairVisE is through Google OAuth, which provides highly reliable security.

##### Serviceability
CharVisE only works over an active internet connection.

## Development Process

Our team adopted an agile way of development. We wanted to deliver features efficiently while compromising unexpected events and adapting with requirement changes along the way. Constant communication and clarification between one another was a major factor in ensuring that the project goes smoothly.

In particular, we followed a *SCRUM* process while working on this project. Koh Chi Hao acted as the *scrum master*, overseeing the overall progress of the project. The team met up face-to-face weekly that simulates a *weekly scrum*, to update the progress of everyone on their tasks, highlight the problems faced during the development process and to plan what each member should do for the week ahead.

Before the start of the project, we formulated a product backlog based on 6 requirements provided by our *product owner* - Doctor Bimlesh using GitHub [issues](https://github.com/CS3219-SE-Principles-and-Patterns/chairvise3-0-ghost-3/issues?q=is%3Aissue+is%3Aclosed). We also drafted a rough timeline for the implementation of each feature and adopted a *bi-weekly sprints*.

The following shows the schedule of what we have done throughout the 6 weeks: 

![](https://i.imgur.com/QpQESXr.png)
![](https://i.imgur.com/k3m91E3.png)

## Enhancements

### 1. Make existing visualisations more meaningful to the user
#### 1a. [US7] Labels for X and Y-axis
As ChairVisE 2.0 only shows the x and y-axis when the cursor is hovers over the chart, this functionality could not be applied to the pdf that is exported. Hence, we have added editable x and y-axis labels to the chart. This will be clearer for the audiences to understand what those axes represent.
![](https://i.imgur.com/u0qjreO.png)
<p align="center">Figure 22. Chart with the addition of x and y labels</p>

#### 1b. [US8] Multiple chart representations
This functionality allows users to toggle certain visualisation analysis from `bar` chart to `pie` chart and vice versa. Hence, this provides users with more chart representation to choose from.

<p align="center">
<img src="https://i.imgur.com/5fgH8KD.jpg"/>
<br>
Figure 23. Bar-Pie Chart Toggle Button
</p>

<p align="center">
<img src="https://i.imgur.com/yUJEcQb.jpg"/> 
<br>
Figure 24. Bar Chart for Submission Rank Track
</p>

<p align="center">
<img src="https://i.imgur.com/JYgg8qL.jpg"/>
<br>
Figure 25. Pie Chart for Submission Rank Track
</p>

In figure 24 to figure 25, it shows the transformation of `bar` chart to `pie` chart for the `submission rank track` analysis.

The following sequence diagram shows the implementation when a user toggles a visualisation from `bar` chart to `pie` chart:

<p align="center">
<img src="https://i.imgur.com/lS0L6ii.jpg"/> 
<br>
Figure 26. Bar Chart to Pie Chart Sequence Diagram
</p>

1. User toggles chart type to `bar` chart and selects `save`. The view (`BasicSectionDetail.vue`) will send a request with `editForm` information to the store (`section.js`)
2. The store will send a HTTP PUT request to the link `/api/presentations/${presentationId}/sections/${id}` to controller (`PreseentationSectionController.java`)
    - `presentationId` (Id of presentation to be updated
    - `sectionId` (Id of presentationSection to be updated
    - `newPresentationSection` (presentationSection to replace)
3. The controller calls `findById(sectionId)` using the passed in sectionId to retrieve the corresponding old presentation section from presentationSection logic (`PresentationSectionLogic.java`)
4. After getting the old presentationSection, it calls presentationSection logic (`PresentationSectionLogic.java`) to update the old presentationSection to the new presentationSection, which returns an updated presentationSection. 
5. Finally, the controller encapsulates the returned presentationSection into a `PresentationSectionRequestResponse` to be returned to the store.

### 2. Add more visualisations to provide more value to the user
#### 2a. [US9] More analysis options
- `Average reviewer expertise level for each author, organization and country. (3 visualisations with different x-axis)`
<br>This chart shows the average reviewer's expertise level that reviews all the submissions for each author. This allows users to know which author's submissions are being reviewed by more expert reviewers. (Same goes for organization and country)

![](https://i.imgur.com/SsybWMF.png)
<p align="center">Figure 27. Average Reviewer's Expertise Level per Author</p><br>

- `Submission Rejected Rank Track and Submission Rejected Rank Author (2 visualisations with different x-axis)`
<br>This chart shows the number of submissions that were rejected in each track. This allows us to get the info on which track gets rejected more often than the other. (Same goes for author)

![](https://i.imgur.com/KF6h8y6.png)
<p align="center">Figure 28. Bar chart of Submission Rejected Rank Track</p><br>

- `Average Number of Days to Review By Track`
<br>This chart shows the average number of days taken to review each track, which gives us an insight on the efficiency of the reviewing process for each track.

![](https://i.imgur.com/B6ynvOl.png)
<p align="center">Figure 29. Bar chart of Average Number of Days to Review By Track</p><br>

- `Average Reviewer's Expertise Level By Track`
<br>This chart shows the average reviewer's expertise level for each track. This provides information on the expected expertise level of reviewers for each track.

![](https://i.imgur.com/SUj12ku.png)
<p align="center">Figure 30. Bar chart of Average Reviewer's Expertise Level By Track</p>

### 3. Persistence of multiple conference data
#### 3a. [US1] Allow multiple uploads for files of the same type (author, review, submission)

Allowing the uploading of multiple conference data allows users to compare different dataset from different files. This will increase user efficiency and the ability to view trends is much easier.

For example, now the user can have the ability to upload multiple author or submission or review `csv` files without overriding the existing content. 

![](https://i.imgur.com/2kHYmVC.png)
<p align="center">Figure 31. Sequence diagram of multiple conference data upload</p><br>

#### 3b. [US2] View files that are uploaded

Users can view all their uploaded files. This allows the user to manage all their uploaded file in a single location. 

<p align="center">
<img src="https://i.imgur.com/33Nx2E0.jpg"/>
<br>
Figure 32. View Uploaded Files Page
</p>

The following sequence diagram shows the implementation of viewing of uploaded files: 

<p align="center">
<img src="https://i.imgur.com/qv3Wbxx.jpg"/> 
<br>
Figure 33. Sequence Diagram for view for uploaded files
</p>

1. When the user selects to view their uploaded files, `UploadedFiles.vue` will send a request the data from the store (`userFile.js`)
2. The store will send a GET request with a request link of `/api/files` to the relevant controller (`UserFileController.java`) 
3. The controller will first verify the login user (with `GateKeeper.java`) and gets the `UserInfo` in return
4. The controller will then call the logic for all the user files by passing the user email in the function `findByUserEmail`
5. The logic will forward the request to fetch from the repository (`UserFileRepository.java`) to fetch the relevant user files.
6. The returned values are returned to the view. 

#### 3c. [US3] Delete files that are uploaded
With the addition of uploading multiple files, we also implemented the option of deleting uploaded files which allow users to de-clutter the number of files uploaded as well as to have better file management.

![](https://i.imgur.com/wWWjoCK.png)
<p align="center">Figure 34. Delete operation in UploadedFiles.vue</p><br>

The following shows the sequence diagram when deleting a certain author file named `author.csv`. The `userFile` is first removed from the database, followed by the corresponding `authorRecord` identified by the primary key, `fileId`. The same sequence diagram can be applied to review and submission files.

![](https://i.imgur.com/Kemt12N.png)
<p align="center">Figure 35. Sequence Diagram for deleting uploaded files</p>

### 4. User interface improvements
#### 4a. [US4] Copy charts to another presentation
This functionality allows users to copy any chart from one presentation to another presentation, which makes the process of creating the same chart information to a different presentation easier.

<p align="center">
<img src="https://i.imgur.com/9KArD7N.jpg"/>
<br>
Figure 36. Copy button on each chart
</p>

<p align="center">
<img src="https://i.imgur.com/IhRUfaD.jpg"/> 
<br>
Figure 37. Popup dialog to select copy to presentation
</p>

The following sequence diagram shows the implementation of copying a chart from one presentation to another presentation:

<p align="center">
<img src="https://i.imgur.com/IKVY767.jpg"/>
<br>
Figure 38. Copy Presentation Sequence Diagram
</p>

1. When the user selects `copy` from the chart, the view (`BasicSectionDetail.vue`) will send a request to open the view (`CopyPresentationPanel.vue`) dialog to allow the user to select which presentation to be copied to.
2. After the user selects a presentation, it will send a request with `presentation` to the store (`section.js`)
3. The store will send a HTTP POST request to link `/api/presentations/${presentationId}/sections/${id}`  to the controller (`PresentationSectionController.java)
- `presentationId` (Id of presentation to be copied to)
- `sectionId` (Id of presentationSection to copy)
4. The controller calls `findById(presentationId)` using the passed in presentationId to retrieve the corresponding presentation from presentation logic (`PresentationLogic.java`) 
5. The controller then calls `findById(sectionId)` using the passed in sectionId to retrieve the corresponding presentation section from presentationSection logic (`PresentationSectionLogic.java`)
6. After getting both presentation and presentationSection, it calls presentationSection logic (`PresentationSectionLogic.java`) to save the presentationSection to the presentation, which returns a new presentationSection.
7. Finally, the controller encapsulates the returned presentationSection into a `PresentationSectionRequestResponse` to be returned to the store.

#### 4b. [US5] Preview first few rows of uploaded file
With this addition, users can view what each column represents when mapping each column to the predefined field, which makes the mapping process easier and more convenient.

![](https://i.imgur.com/ztrncG0.png)
<p align="center">Figure 39. Preview of uploaded data</p><br>

#### 4c. [US6] Selection of specific files to be analysed
Having the ability to upload multiple files, this feature allows the user to select the files they would like to include in the visualization. This feature provides a drop-down to allow the user to select the different table types of file they want and the section will be filtered according to the combination of the files they have picked. 

<p align="center">
<img src="https://i.imgur.com/FvHpDkB.jpg"/>
<br>
Figure 40. Select files for visualization
</p>

The following sequence diagram shows the implementation of adding a new chart section: 

<p align="center">
<img src="https://i.imgur.com/mm5qFRj.png"/> 
<br>
Figure 41. Sequence Diagram for adding a new chart section
</p>

1. After user selects `Add New Section`, the view (`SectionListPanel.vue`) will send a request with the `presentationId`, `selectedNewSection`, `dataSet` (userEmail) and `keys` (fileIds) to the store (`section.js`)
2. The store will send a HTTP POST request to the link `/api/presentations/${presentationId}/sections` with the body content containing `presentationSection` and `fileIds` to the controller (`PresentationSectionController.java`)
3. The request is encapsulated in the `PresentationSectionRequestResponse` object
4. The controller first call the passed in `PresentationSectionRequestResponse` to get corresponding presentationSection and calls the presentationSection logic (`PresentationSectionLogic.java`) to save the new presentationSection, which returns a new presentationSection
5. The controller then calls the loops through the fileIds with `PresentationSectionRequestResponse` and call the presentationSectionUserFile logic (`PresentationUserFileLogic.java`) to save the presentationUserFile, which returns a new presentationSectionUserFile.
6. After getting the presentationSectionUserFile, the controller proceed to call the returned `PresentationSectionUserFile` to retrieve the fileId and push it to the list of fileIds.
7. The controller finally encapsulates both the returned presentationSection and the list of fileIds into a `PresentationSectionRequestResponse` to be return to the store. 

#### 4d. [US10] Re-ordering of controls in advanced edit mode
The controls in advanced edit mode are rearranged to be at the same level as their sections for a better visual flow.

![](https://i.imgur.com/vpvH5iS.png)
<p align="center">Figure 42. Updated Positions of Advanced Edit Controls</p>

#### 4e. [US13] Dark Mode
In this day and age, every piece of software comes with a dark mode because dark mode is proven to be less straining on the eyes. There is a toggle switch on the top right-hand side of the navigation bar where the user can toggle between `light` and `dark` mode.

![darkMode](https://i.imgur.com/YKQEsfX.png)
<p align="center">Figure 43. Light mode (Home page)</p>

<br>

![darkMode1](https://i.imgur.com/KdHwzxY.png)
<p align="center">Figure 44. Dark mode (Home page)</p>

<br>
<br>

![darkMode2](https://i.imgur.com/gYwrW0B.png)
<p align="center">Figure 45. Dark mode (Analyze page)</p>

#### 4f. [US15] Shift help instructions to an individual help page
As ChairVisE 2.0 help instruction is placed in the home page. Hence, we have separated it from `home page` to `help page`. This will be clearer for the users to find help instruction from the navigation bar `help`.

<p align="center">
<img src="https://i.imgur.com/Cx6GZZF.jpg"/>
<br>
Figure 46. Help Page
</p>

#### 4g. [US16] Confirmation prompt before deletion (uploaded files, presentations, visualisations)
A confirmation prompt will pop up before the user deletes the particular item to prevent them from mistakenly delete important files, presentations and visualisations. The prompts will have the following format:

![](https://i.imgur.com/7cMyJJd.png)
<p align="center">Figure 47. Confirmation prompt for deletion</p>

### 5. Co-authorship Queries
#### 5a. [US14] Co-authorship Queries
By having the different visualization for co-authorship, it enables the user to see more potential trends between `countries`,`authors` and `organization`. The different pair of collaboration between `country-country`, `author-author` and `organization-organization` is available to the user.

![fig48](https://i.imgur.com/nSLnLUh.png)
<p align="center">Figure 48. Visualization for author-author pair</p><br>

![fig49](https://i.imgur.com/sEuZHYw.png)
<p align="center">Figure 49. Visualization for country-country pair</p><br>

![fig50](https://i.imgur.com/pWV5HpB.png)
<p align="center">Figure 50. Visualization for organization-organization pair</p><br>

### 6. Others
#### 6a. [US11] Email Notifications when sharing to other users

Whenever user *A* shares a presentation with user *B* via email, using `SendGrid` email feature, an email will be sent to the user *B* email notifying that someone has shared with them a presentation with the ability to `edit` or `view` only.

![](https://i.imgur.com/hMiUchE.png)
<p align="center">Figure 51. Sequence diagram for email notification</p><br>

Once the email has been sent out by the SendGrid system, user *B* will receive the email. From the email, user *B* can simply click on *Open in ChairVisE* button and they will be redirected to ChairVisE website with the presentation. A screenshot of how the email looks like is provided below.

![fig52](https://i.imgur.com/BECsztO.png)
<p align="center">Figure 52. User receiving email from SendGrid</p><br>

## Design

### Design Decisions
This section highlights the design decisions that we have adopted,  either continuing from the existing ChairVisE 2.0 or the design decisions we have chosen for the new features implemented. 

#### MVC Pattern for Front-End
In ChairVisE, the Front-End adopts an MVC pattern, where `Model` is represented by the different variables within the store, `Controller` is represented by the methods provided within the store and can be called by `View` and `View` is represented by the `.vue` files itself. 

We chose to adopt the existing MVC framework used as it segregates the responsibility of what each component should do. By using the store as the single source of truth, it allows different `View` components to access the data within it, without having to pass the data between components. This de-couples the components within `View`. On top of that, the store also provides methods which act as a `Controller` for the `View` components to update the data within the store. 

#### Predefined Queries and Analysis Logic
The `predefinedQueries.js` file is build to be extensible only to a certain extent. For example, the `predefinedQueries.js` only allows other developers to build simple SQL queries that require only 1 or multiple different tables. This violates the Open-Closed Principle and is a bad design pattern because it does not allow other types of SQL queries. What if in the future, we need to add new SQL queries that are complex and have nested queries. How can we do that with the current `predefinedQueries.js`? 

On top of that, the way the SQL queries are being formed within the `AnalysisLogic.java` has to concatenate in a certain way that does not allow the SQL query to be flexible. Currently it follows this particular order: `SELECT %s FROM %s`, `WHERE %s`, `AND %s`, `GROUP BY %s`, `ORDER BY %s`. This particular order assumes that within each SQL statement there will be only an `AND` statement and no `OR` statement. This is certainly not true if the goal is to be extensible. 

Furthermore, within the `AnalysisLogic.java`, there is a particular snippet of code that does not allow SQL tables to be renamed. 

```java
 String tablesStr = analysisRequest.getInvolvedRecords().stream()
                .map(PresentationSection.Record::getName)
                .collect(Collectors.joining(","));
```

This snippet of code assumes that all SQL tables will be joined with a comma. This will eliminate certain features of SQL such as renaming tables and joining of multiple same tables. 

To solve this problem, there should be an additional field within the `predefinedQueries.js` called `rename`. The following snippet shows how it can be implemented in future.

```jsonld
involvedRecords: [
    {
      name: 'submission_record',
      customized: false,
      rename: 'sr1'
    },
    {
      name: 'submission_record',
      customized: false,
      rename: 'sr2'
    }
],
```

#### [US1] New user file table 
##### Possibilities 
To make this feature possible, a new `entity` called `UserFile.java` has been created, which stores the `file_name`, `file_type`, `table_type` and other details of a particular file. The reason why we need to store all these fields is that with this new `UserFile.java` entity, it opens up new possibilities like analyzing multiple files, managing uploaded files and analyzing multiple same file type.

##### Problems
In the process of building this feature, all the test cases written for `CharVisE 2.0` broke. The reason is because of the way different `Record` related entities are designed. They are not designed with extensibility in mind. When we decide to add the field `fileId` to the `Author`, `Review` and `Submission` entities, a lot of classes that are dependent on these entities have broke. This is a clear sign that there are a lot of dependencies going on within the different classes which is an anti-pattern.

On top of that, the test cases for the `RecordController.java` broke because of the change in the HTTP body request. The way the test is designed does not allow changes within the HTTP body request. This is detrimental to the development process because when an entity changes, it will break the different test cases.

This is why we chose not to update all the 60 test cases because of bad design and limited development time frame.

#### [US6] Storage of file ids for charts
To allow storing of file ids, we have to make additional changes to store the relevant file ids in `predefinedQueries.js`, passing of the file ids to the backend API and add additional tables to store those file ids. 

##### File IDs in `predefinedQueries.js`
Rather than adding the file ids within the `data` portion within each query, we segregated the file ids to be on its own. The reason that we took this approach is that, if we were to put the file ids within the `data` portion, we will have to edit the existing `presentationSection` model structure, which could take us a lot of time to fix up those changes.

##### Passing of file ids to backend API. 
With the addition of the new file ids, we changed how we passed the data from the store to the backend API. Previously, we only pass the `presentationSection` data to the backend API through the body. With the addition of file ids, we encapsulated the `presentationSection` data with the file ids as one object to pass it to the backend API. 


With this new object passed to the backend API, `presentationSectionController` which was responsible for handling the API call has to be changed as well. We created a new object (`PresentationSectionRequestResponse`) to handle this new request body type that is passed in and also to encapsulate the similar object type to pass back to the front-end to handle the response after. 

##### Additional DB tables to store the file ids. 
These additional file ids require a change in the DB structure to store it. We added a table (`PresentationSectionUserFile`) to store the relationship between each presentation section and their file ids. 

We adopted this approach of storing the relationship between the presentation section and their file ids over storing the file ids as a list within the `PresentationSection` to cater for the ease of future edits of the file ids for each chart. Also, adopting the current approach allows us to clearly define the relationship presentationSection and their file ids. 

#### [US11] Email Notification

##### Configuration
When building the email notification feature, we have a class `SendGridConfig.java` that makes use the springboot `@Configuration` annotation to automatically add this class as a dependency to the application. Since the configuration is automatically added to the application, the auto-configuration is made easier by eliminating the need for defining certain beans that are included in the auto-configuration classes.

##### Bean for SendGrid

Within this class, we have a `@Bean` to provide the `SendGrid` class which by default is a Singleton in the eyes of SpringBoot. The reason why we chose to have a single instance of the `SendGrid` class is that we should manage access to the `SendGrid` class which is shared by the entire application. This allows any part of the application to send an email for future implementation.

##### Builder Pattern

Additionally, we have decided to use a builder pattern to create our `Mail` class. We have 5 methods that helps to create the `Mail`  class `from()`, `replyTo()`, `to()`, `addPersonalization()`, `build()`. The reason why we have decided to choose this pattern is that there is a specific set of steps to follow before creating the `Mail` class.

Furthermore, there may be a need to create different types of emails in the future. By separating these different methods, we allow this `EmailBuilder.java` to be extended in the future.

To create the `Mail` class we have to follow this particular order:
```java
 Mail mail = new SendGridEmailBuilder(templateId)
                .from(fromEmailAddress)
                .replyTo(fromName)
                .to(toEmailAddress)
                .addPersonalization(dynamicTemplateData)
                .build();
```
The builder pattern allow us to not have a giant constructor that accepts all the different parameters required to build a `Mail` class.

### ER Diagrams
The following ER Diagram shows the relationship between each table in ChairVisE 3.0: 
![fig53](https://i.imgur.com/ZIXCjsy.png)
<p align="center">Figure 53. ER Diagram of ChairVisE 3.0</p>

## Future Developments 

### 1. Enhancement to [US2] & [US3]

#### 1a. Further management of uploaded files
The current implementation only allows the user to view and delete all the files they have uploaded. Future development could add the capabilities to edit the file names or preview the data of uploaded files. 

### 2. Enhancement to [US6] 

#### 2a. Selection multiple files of the same table type
Currently, users can only select one of each of the same table type and a combination of different table types for analysis. For example, if the users have uploaded 2 table type of `author`, they can only select one of the `author` files that they have uploaded for analysis. Future implementation could look to allow multiple of the same table type (E.g. two `author` type files) and join the data from the 2 files for analysis.

#### 2b. Changing of file ids under advanced filters 
With the current implementation of allowing users to select the file they wish to be analysed, there's a need to state which files should be included in the charts. With that, we associated each analysis options to the relevant files that the analysis would need, and some of the file ids are specified under the `filter` field under `predefinedQueries.js`. The current implementation does not allow the user to edit the files associated with the analysis already created. Future implementation could look to allow the edits of those file ids under the advanced edit option. 

### 3. Rigidity of predefinedQueries.js in Frontend codebase
Currently, the `predefinedQueries.js` file acts as a storage for all the different queries for the different ways a user can view the different visualization. The structure of  `predefinedQueries.js`  is rigid and does not allow extensions for complex SQL queries like joining multiple tables of the same name or even nested queries. This impedes the growth of the visualisations that can be produced for ChairVisE 3.0.

Additionally, the SQL statement is stored inside the `predefinedQueries.js` and this opens the application to certain web vulnerabilities like SQL Injection. A simple man-in-the-middle would allow the attacker to change the query and get anything within the database. 

To mitigate this problem, there should be an API call to the backend with the type of visualization that the user would like to create. This way, there is no need to hardcode the SQL queries in the frontend and the logic to create a visualization can be store in the backend where it should be. As such, the application becomes extensible in the sense that, the backend can always add new visualization without ever disrupting the frontend.

### 4. Presentation Mode
The current ChairVisE only allows exporting of presentations as `pdf` files. Interactions to the charts (e.g. tool-tip hovers that show additional information of the chart) cannot be shown in the exported `pdf` files. 

A suggestion is to add a presentation mode, which shows presentations without controls (edit and delete), while still allowing the interactions to the charts. In this way, the presenter can show more chart info that is not encapsulated when the presentation is exported as `pdf`.

## Glossary 

#### Modern Browsers
Chrome, Safari, Firefox, Microsoft Edge

#### MVC
Model-View-Controller pattern

#### Google OAuth
Google's login mechanism

#### Visualization
Graphs representing the different aspects of the dataset
