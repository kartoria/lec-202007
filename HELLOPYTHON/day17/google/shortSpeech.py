from google.cloud import speech
import io, os

os.environ["GOOGLE_APPLICATION_CREDENTIALS"]="My Project 55374-0e1821e0267a.json"
client = speech.SpeechClient()
speech_file = "음성 002.wav"
with io.open(speech_file, "rb") as audio_file:
    content = audio_file.read()

audio = speech.RecognitionAudio(content=content)

#https://cloud.google.com/speech-to-text/docs/reference/rest/v1p1beta1/RecognitionConfig#AudioEncoding
config = speech.RecognitionConfig(
    encoding=speech.RecognitionConfig.AudioEncoding.LINEAR16,
    sample_rate_hertz=48000,
    language_code="ko-KR",
    audio_channel_count = 2
)

response = client.recognize(config=config, audio=audio)

# Each result is for a consecutive portion of the audio. Iterate through
# them to get the transcripts for the entire audio file.
for result in response.results:
    # The first alternative is the most likely one for this portion.
    print(u"Transcript: {}".format(result.alternatives[0].transcript))