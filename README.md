# Blog API

This API provides endpoints to manage blog posts and comments.

## Postman Collection:- 

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://god.gw.postman.com/run-collection/22670645-5b8cbe90-5322-41c2-b72e-1bd1fec0d648?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D22670645-5b8cbe90-5322-41c2-b72e-1bd1fec0d648%26entityType%3Dcollection%26workspaceId%3D53b2f003-d9b9-42a7-8827-efbec6477f69)

## How to deploy and Run?

1. cd into the directory where the Dockerfile is located
2. run the following command if youre running this project for the first time
   
   **docker build -t blogapi .**
   
4. once the image is downloaded you can run the following command to get the project up and running
   
   **docker run -p 8080:8080 blogapi**



## Endpoints
The base url is http://localhost:8080 

(append the base url to each individual endpoints)
### Get All Posts

Retrieve a list of all blog posts.

- **URL:** `/api/v1/posts`
- **Method:** `GET`
- **Response:** Array of blog post objects.

### Get Post by ID

Retrieve a specific blog post by its ID.

- **URL:** `/api/v1/posts/{id}`
- **Method:** `GET`
- **Parameter:** `id` (integer) - The unique identifier of the blog post.
- **Response:** A single blog post object.

### Create New Post

Create a new blog post.

- **URL:** `/api/v1/posts`
- **Method:** `POST`
- **Request Body:** Blog post object (title, content, author).
- **Response:** The created blog post object with its assigned ID.

### Update Post

Update an existing blog post by its ID.

- **URL:** `/api/v1/posts/{id}`
- **Method:** `PUT`
- **Parameter:** `id` (integer) - The unique identifier of the blog post.
- **Request Body:** Updated blog post object (title, content, author).
- **Response:** The updated blog post object.

### Delete Post

Delete a blog post by its ID.

- **URL:** `/api/v1/posts/{id}`
- **Method:** `DELETE`
- **Parameter:** `id` (integer) - The unique identifier of the blog post.
- **Response:** Success message or status.

### Get Comments for Post

Retrieve all comments for a specific blog post.

- **URL:** `/api/v1/posts/{postId}/comments`
- **Method:** `GET`
- **Parameter:** `postId` (integer) - The unique identifier of the blog post.
- **Response:** Array of comment objects.

### Create New Comment for Post

Create a new comment for a specific blog post.

- **URL:** `/api/v1/posts/{postId}/comments`
- **Method:** `POST`
- **Parameter:** `postId` (integer) - The unique identifier of the blog post.
- **Request Body:** Comment object (content, author).
- **Response:** The created comment object with its assigned ID.


