# Drm Protection

Viblast Android SDK supports `PlayReady`, `Widevine`, `ClearKey` or Multi-DRM protected DASH streams.


## How it works
To enable `DRM` support, the Application must provide `ViblastDrmCallback` object when the player is created. If the object is `null`, the Viblast Player will not handle `DRM` protected streams.

When the playback of protected stream starts:
1. The Renderer detects the available `CDM` pssh boxes.
2. Then internally notifies the player that the stream is protected with a list of applied `CDMs`.
3. Viblast Player selects device supported `CDMs` from the list and calls `onDrmInfoNeed` of the `ViblastDrmCallback` object.
4. The Application chooses the desired CDM and returns it with the `ViblastDRMInfo` object as a result of the callback.
5. Viblast Player uses the result object `ViblastDrmInfo` in order to initialize the `CDM` and provision the key acquisition requests.
6. If the result is `null` then the player will inform the Renderer that the `DRM` is not supported and the playback will not continue.

## Viblast DRM API

`com.viblast.android.drm`
### Interface `ViblastDrmCallback`
Implementation of this interface is passed to the ViblastPlayer, allowing it to interact with the calling application to retrieve `CDM` specific data.
#### Method summary
```
ViblastDRMInfo onDrmInfoNeed(Set<UUID> cdmUUIDs);
```
- Returns a ViblastDRMInfo object contains the desired `CDM` initialization data. Might be `null` if the application does not support any of the passed `CDMs`.
- parameter cdmUUIDs - a set of `CDM` UUIDs supported by the device and applicable for the protected stream.

### Class ViblastDRMInfo
Provides the information need for initialization and operation of the desired `CDM`.
#### Method summary
```
ViblastDrmInfo(UUID selectedCdmUUID, String licenseServerUrl)
  throws IllegalArgumentException;
```
- Creates an object with the given `CDM` UUID and the DRM license server `url`.
- throws IllegalArgumentException if the selectedCdmUUID is null

```
String getLicenseServerUrl();
```
- Returns the license server `url`

```
String getSelectedCdmUUID();
```
- Returns the `CDM` UUID

```
void addDrmKeyRequestProperty(String key, String value);
```
- Adds key request property. The key will be add as a header to the http requests performed by the underlying security module.  

```
void removeDrmKeyRequestProperty(String key);
```
- Removes a key request property.

```
void removeAllDrmKeyRequestProperties(String key);
```
- Removes all keys request properties.

```
String getDrmKeyRequestProperty(String key);
```
- Returns the value of the given key. Might be null.

```
Map<String, String> getAllDrmKeyRequestProperties();
```
- Returns all added keys.
