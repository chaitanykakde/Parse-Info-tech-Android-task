# JSONBin Setup Instructions

## Step 1: Upload Course Data to JSONBin

1. Go to [JSONBin.io](https://jsonbin.io/)
2. Create a free account
3. Click "Create Bin"
4. Copy the contents of `courses_data.json` file
5. Paste it into the JSONBin editor
6. Save the bin
7. Copy the Bin ID from the URL (it looks like: `https://api.jsonbin.io/v3/b/YOUR_BIN_ID`)

## Step 2: Update API Configuration

1. Open `app/src/main/java/com/chaitany/chaitanystask/network/ApiService.kt`
2. Replace `YOUR_BIN_ID` with your actual Bin ID from JSONBin
3. Update the BASE_URL in `RetrofitClient.kt` if needed

## Step 3: Add API Key (Optional but Recommended)

For better rate limits and security:

1. In JSONBin, go to your profile and create an API key
2. Add the API key to your app by updating `RetrofitClient.kt`:

```kotlin
private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-Master-Key", "YOUR_API_KEY_HERE")
            .build()
        chain.proceed(request)
    }
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()
```

## Step 4: Test the API

The app will automatically fetch courses from your JSONBin endpoint. If the API fails, it will fallback to sample data.

## JSONBin Endpoint Format

Your endpoint will look like:
```
https://api.jsonbin.io/v3/b/YOUR_BIN_ID/latest
```

## Course Data Structure

The JSON file contains 10 courses with the following structure:
- **id**: Unique identifier
- **title**: Course name
- **description**: Course description
- **price**: Course price in USD
- **rating**: Course rating (1-5)
- **imageUrl**: Course image URL
- **instructor**: Instructor name
- **duration**: Course duration
- **category**: Course category

## Features

- ✅ Real API integration with JSONBin
- ✅ Fallback to sample data if API fails
- ✅ Error handling and loading states
- ✅ Beautiful course cards with images
- ✅ Pricing and rating display
- ✅ Category tags
- ✅ Instructor information
