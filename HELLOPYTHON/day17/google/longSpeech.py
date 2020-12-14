"""Transcribe the given audio file asynchronously."""
from google.cloud import speech
import os
import io

os.environ["GOOGLE_APPLICATION_CREDENTIALS"]="My Project 55374-0e1821e0267a.json"
client = speech.SpeechClient()
speech_file = "2Pac - Life Goes On.mp3"

with io.open(speech_file, "rb") as audio_file:
    content = audio_file.read()

"""
 Note that transcription is limited to 60 seconds audio.
 Use a GCS file for audio longer than 1 minute.
"""
audio = speech.RecognitionAudio(content=content)

config = speech.RecognitionConfig(
    encoding=speech.RecognitionConfig.AudioEncoding.FLAC,
    sample_rate_hertz=44100,
    language_code="ko-kr",
    audio_channel_count = 2 
)
operation = client.long_running_recognize(config=config, audio=audio)

print("Waiting for operation to complete...")
response = operation.result(timeout=90)

# Each result is for a consecutive portion of the audio. Iterate through
# them to get the transcripts for the entire audio file.
for result in response.results:
    # The first alternative is the most likely one for this portion.
    print(u"Transcript: {}".format(result.alternatives[0].transcript))
    print("Confidence: {}".format(result.alternatives[0].confidence))
