# NBAChampionList

### Build Instructions
Nothing special going on here, you should be able to run this in Android Studio 3.6.3 by pressing the run button. Same for tests, you should be able to run them without any special setup.

### Assumptions & Design Decisions
As instructed the UI is very simple. I have used a floating action button to allow users to add items to the list. I opted to use a bottom sheet for text entry since it's a nicer pattern than a more traditional alert dialog. Once the item is created I simply add it to the end of the list but I could imagine there are improvements that could be made here such inserting in sorted order (either by year or team name) but sorting was outside of the scope of this project.

### Limitations
User entered list data is not persisted and I would imagine saving created items to a local database and syncing to a server for a better long term solution. Tests are very simple to illustrate the approach but for a larger project I would add more testing for error/failure states and consider UI tests but the logic for fetching data, adding items and displaying a list is all tested.
