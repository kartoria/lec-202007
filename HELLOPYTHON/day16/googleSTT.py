from google.cloud import speech
import io, os

os.environ["GOOGLE_APPLICATION_CREDENTIALS"]="d:/mykey.json"
client = speech.SpeechClient()

with io.open("2Pac - Life Goes On.mp3", "rb") as audio_file:
    content = audio_file.read()

audio = speech.RecognitionAudio(content=content)
config = speech.RecognitionConfig(
    encoding=speech.RecognitionConfig.AudioEncoding.LINEAR16,
    sample_rate_hertz=16000,
    language_code="en-US",
)

response = client.recognize(config=config, audio=audio)

# Each result is for a consecutive portion of the audio. Iterate through
# them to get the transcripts for the entire audio file.
for result in response.results:
    # The first alternative is the most likely one for this portion.
    print(u"Transcript: {}".format(result.alternatives[0].transcript))
