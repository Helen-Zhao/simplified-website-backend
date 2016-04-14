#Requirements
A web site that hosts such things as texts, music, and other such content typically consists of two parts: the client - the bit that users interact with via their web browsers, and the server - the bit that provides the majority of the functionality. In this assignment, you are responsible for implementing an extremely simplified version of a server for a content website. This implementation will not deal with the content directly, but just with the data about the content, the metadata.

The website has to support adding new users to the system. Once added, users can upload their content. Once uploaded, users can add more information to each content item, such as titles (short descriptive text), tags (keywords that can be used to organise and be used for searching), descriptions (non-searchable), time and dates, and so on. The content can be organised in to folders (collections of content). Folders can also have titles, tags, and descriptions. Content can belong to more than one folder, or none (really just an untitled Folder).

Any content item uploaded by a user is owned by that user. The owner of a content item can set or change the title, description, tags, or any other metadata relating to it.

The creator of an Folder is its owner. Folders can contain any content owned by the user, but can only contain content (that is, you cannot nest folders inside of folders), but content can appear in multiple folders.

Content also has visibility, which indicates who can see that content. Public content can be seen by all other users, whereas Private content can only be seen by the user. Content with Friends visibility can be seen by any friend of the user.

Users can have friends. A friend of a user can see all content owned by the user with Friends visibility (as well as that with Public visibility). The Friends relationship is one way - one user can be a friend of the other but not vice versa.

Normally the texts and metadata would be in a database of some kind on the server. We can't do that so instead we will read data in from a file. What is to be done with that data is supplied via a separate instruction. The format of the data and the instructions is given below.

#Functionality
1. List all content in the system in visibility order (Public, Friends, Private), and for those with the same visibility, in alphabetical order of title. Use the format [type]:[title] for each content item (do not show the '[' and ']', but do show the ':'). LISTCONTENT
2. List all members in the system in alphabetical order of name. Use the format [member name]([nickname]) for each member. LISTMEMBERS
3. For a specified folder, list all content in a specified folder in visibility order (first Public, the Friends, then Private) and for those with the same visibility in alphabetical order of title. Use the format [visibility] [content title]([folder title]) LISTFOLDER:folder title
4. For a specified member and visibility, list all content owned by that member with the specified visibility in alphabetical order of title. Use the format [member name] [content title] ([visibility]) LISTMEMBERCONTENT:member name:visibility
5. For a specified member, list all content that member can see that is not owned by that member. The order is alphabetical by title (that is, visibility is ignored in this case). Use the format [content title] ([owner name], visibility). LISTVISIBLECONTENT:member name
6. For a specified member and tag, list all content that that member can see, including that owned by that member, that has that tag. The order is alphabetical by title (ignore visibility). Use the format [content title] ([owner name], [visibility], [all tags]) LISTCONTENTWITHTAG:member name:tag
7. List all content that is not owned by any member. The order is visibility order (Public, Friends, Private), and for those with the same visibility in alphabetical order of title. Use the format [content title] ([visibility]). LISTUNOWNED
8. List all members that do not own any content. The order is alphabetical order of name. Use the format [member name]. LISTCONTENTLESS

#Usage
The expected usage for this system is:
command_line_prompt> java -jar <runnablejarfile> <datafile> <command>


#Input Format
The input consists of 4 sections in the following order:
Members. This section lists all the members. Information for each member is on one line. The line consists of three fields. The first field is the string MEMBER, the second is the member's name, and the third is the member's nickname. No two members can have the same name (that is, names must be unique), but they can have the same nickname. The fields are separated by a single tab character.

Friends. This lists all the friends of a member. Each line consists of the 3 fields; the string FRIEND, the name of the member, and the name of the friend, again tab separated. If a member has multiple friends, then there will be multiple lines for that member.

Content. This lists all the content. Each line consists of 5 fields. The first field indicates the type of the content, either TEXT or MUSIC. The next field is the name of the owner. Field 3 is the name of the content, which must be unique. Field 4 is the visibility of the content, one of Public, Friends, or Private. Field 5 is a comma-separated list of tags.

Folders. This section lists what folders the content belongs to (if any). The format is there are 4 fields: the string FOLDER, the name of the owner of the folder, the name of the folder, which must be unique, and the name of the content in the folder. When there are multiple content items in an folder, there will be multiple lines, one for each item.

Also, the input uses "#" at the beginning of a line as a comment character, and such lines are to be ignored. Also, lines that are empty or blank should also be just ignored.

The sections of the input are expected to be in the order above.

See sampleInput.txt for an example.

#Output format
The output for each command is shown below. In each case, the first line is a timestamp indicating when the output was produced, followed by the command and its parameters, followed by the required output.
Below is an example of how your program is expected to be used.

prompt> java -jar se251_a02_upi001.jar tiny_new.csv "LISTCONTENTWITHTAG:William Michael Albert Broad:sushi"

Tue Apr 08 10:57:46 NZST 2014

LISTCONTENTWITHTAG:William Michael Albert Broad:sushi

Great Sushi(William Michael Albert Broad, Friends, sushi)

Lunch@Freds(William Michael Albert Broad, Public, sushi)

St Pierre's on Symonds(Farrokh Bulsara, Friends, auckland,sushi)


The file se251_a02_upi001.jar is the executable jar file. The first parameter (tiny_new.csv) is the name of the input file. The second parameter is the command to execute on that input file. The second parameter is in double quotes (") because it contains spaces in it. Once that value reaches your program it will have lost the quotes but retained any spaces.
The content "Lunch@Freds" is included in the output because it has "sushi" as one of the tags and it has Public visibility.

The content "Great Sushi" is included in the output because it has "sushi" as one of the tags, and it is owned by the specified member. [new!]

The content "St Pierre's on Symonds" is included in the output because it has "sushi" as on the of tags, it has Friends visibility, it is owned by "Farrokh Bulsara", and "William Michal Albert Broad" is a friend of "Farrokh Bulsara", meaning "William" can see any of "Farrokh's" content that is Friends visibility.

If there are problems with the input file or commands, then report each one you find with detail on where the problem is. Depending on what order you check things, you may or may not be able to report all actual problems in a given file, but you do need to make sure you do not use invalid data when processing a command. Report the problems after the output from the command, making it clear which is output and which is reporting problems.
