/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-06-26 16:27:34 UTC)
 * on 2013-07-08 at 14:07:09 UTC 
 * Modify at your own risk.
 */

package com.example.treasurehunt.targetendpoint;

/**
 * Service definition for Targetendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link TargetendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Targetendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.15.0-rc of the  library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://treasurehuntandroid.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "targetendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Targetendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Targetendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getTarget".
   *
   * This request holds the parameters needed by the the targetendpoint server.  After setting any
   * optional parameters, call the {@link GetTarget#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetTarget getTarget(java.lang.String id) throws java.io.IOException {
    GetTarget result = new GetTarget(id);
    initialize(result);
    return result;
  }

  public class GetTarget extends TargetendpointRequest<com.example.treasurehunt.targetendpoint.model.Target> {

    private static final String REST_PATH = "target/{id}";

    /**
     * Create a request for the method "getTarget".
     *
     * This request holds the parameters needed by the the targetendpoint server.  After setting any
     * optional parameters, call the {@link GetTarget#execute()} method to invoke the remote
     * operation. <p> {@link
     * GetTarget#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetTarget(java.lang.String id) {
      super(Targetendpoint.this, "GET", REST_PATH, null, com.example.treasurehunt.targetendpoint.model.Target.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetTarget setAlt(java.lang.String alt) {
      return (GetTarget) super.setAlt(alt);
    }

    @Override
    public GetTarget setFields(java.lang.String fields) {
      return (GetTarget) super.setFields(fields);
    }

    @Override
    public GetTarget setKey(java.lang.String key) {
      return (GetTarget) super.setKey(key);
    }

    @Override
    public GetTarget setOauthToken(java.lang.String oauthToken) {
      return (GetTarget) super.setOauthToken(oauthToken);
    }

    @Override
    public GetTarget setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetTarget) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetTarget setQuotaUser(java.lang.String quotaUser) {
      return (GetTarget) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetTarget setUserIp(java.lang.String userIp) {
      return (GetTarget) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String id;

    /**

     */
    public java.lang.String getId() {
      return id;
    }

    public GetTarget setId(java.lang.String id) {
      this.id = id;
      return this;
    }

    @Override
    public GetTarget set(String parameterName, Object value) {
      return (GetTarget) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertTarget".
   *
   * This request holds the parameters needed by the the targetendpoint server.  After setting any
   * optional parameters, call the {@link InsertTarget#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.example.treasurehunt.targetendpoint.model.Target}
   * @return the request
   */
  public InsertTarget insertTarget(com.example.treasurehunt.targetendpoint.model.Target content) throws java.io.IOException {
    InsertTarget result = new InsertTarget(content);
    initialize(result);
    return result;
  }

  public class InsertTarget extends TargetendpointRequest<com.example.treasurehunt.targetendpoint.model.Target> {

    private static final String REST_PATH = "target";

    /**
     * Create a request for the method "insertTarget".
     *
     * This request holds the parameters needed by the the targetendpoint server.  After setting any
     * optional parameters, call the {@link InsertTarget#execute()} method to invoke the remote
     * operation. <p> {@link
     * InsertTarget#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.example.treasurehunt.targetendpoint.model.Target}
     * @since 1.13
     */
    protected InsertTarget(com.example.treasurehunt.targetendpoint.model.Target content) {
      super(Targetendpoint.this, "POST", REST_PATH, content, com.example.treasurehunt.targetendpoint.model.Target.class);
    }

    @Override
    public InsertTarget setAlt(java.lang.String alt) {
      return (InsertTarget) super.setAlt(alt);
    }

    @Override
    public InsertTarget setFields(java.lang.String fields) {
      return (InsertTarget) super.setFields(fields);
    }

    @Override
    public InsertTarget setKey(java.lang.String key) {
      return (InsertTarget) super.setKey(key);
    }

    @Override
    public InsertTarget setOauthToken(java.lang.String oauthToken) {
      return (InsertTarget) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertTarget setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertTarget) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertTarget setQuotaUser(java.lang.String quotaUser) {
      return (InsertTarget) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertTarget setUserIp(java.lang.String userIp) {
      return (InsertTarget) super.setUserIp(userIp);
    }

    @Override
    public InsertTarget set(String parameterName, Object value) {
      return (InsertTarget) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listTarget".
   *
   * This request holds the parameters needed by the the targetendpoint server.  After setting any
   * optional parameters, call the {@link ListTarget#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListTarget listTarget() throws java.io.IOException {
    ListTarget result = new ListTarget();
    initialize(result);
    return result;
  }

  public class ListTarget extends TargetendpointRequest<com.example.treasurehunt.targetendpoint.model.CollectionResponseTarget> {

    private static final String REST_PATH = "target";

    /**
     * Create a request for the method "listTarget".
     *
     * This request holds the parameters needed by the the targetendpoint server.  After setting any
     * optional parameters, call the {@link ListTarget#execute()} method to invoke the remote
     * operation. <p> {@link
     * ListTarget#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListTarget() {
      super(Targetendpoint.this, "GET", REST_PATH, null, com.example.treasurehunt.targetendpoint.model.CollectionResponseTarget.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListTarget setAlt(java.lang.String alt) {
      return (ListTarget) super.setAlt(alt);
    }

    @Override
    public ListTarget setFields(java.lang.String fields) {
      return (ListTarget) super.setFields(fields);
    }

    @Override
    public ListTarget setKey(java.lang.String key) {
      return (ListTarget) super.setKey(key);
    }

    @Override
    public ListTarget setOauthToken(java.lang.String oauthToken) {
      return (ListTarget) super.setOauthToken(oauthToken);
    }

    @Override
    public ListTarget setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListTarget) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListTarget setQuotaUser(java.lang.String quotaUser) {
      return (ListTarget) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListTarget setUserIp(java.lang.String userIp) {
      return (ListTarget) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListTarget setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListTarget setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListTarget set(String parameterName, Object value) {
      return (ListTarget) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeTarget".
   *
   * This request holds the parameters needed by the the targetendpoint server.  After setting any
   * optional parameters, call the {@link RemoveTarget#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveTarget removeTarget(java.lang.String id) throws java.io.IOException {
    RemoveTarget result = new RemoveTarget(id);
    initialize(result);
    return result;
  }

  public class RemoveTarget extends TargetendpointRequest<com.example.treasurehunt.targetendpoint.model.Target> {

    private static final String REST_PATH = "target/{id}";

    /**
     * Create a request for the method "removeTarget".
     *
     * This request holds the parameters needed by the the targetendpoint server.  After setting any
     * optional parameters, call the {@link RemoveTarget#execute()} method to invoke the remote
     * operation. <p> {@link
     * RemoveTarget#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveTarget(java.lang.String id) {
      super(Targetendpoint.this, "DELETE", REST_PATH, null, com.example.treasurehunt.targetendpoint.model.Target.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveTarget setAlt(java.lang.String alt) {
      return (RemoveTarget) super.setAlt(alt);
    }

    @Override
    public RemoveTarget setFields(java.lang.String fields) {
      return (RemoveTarget) super.setFields(fields);
    }

    @Override
    public RemoveTarget setKey(java.lang.String key) {
      return (RemoveTarget) super.setKey(key);
    }

    @Override
    public RemoveTarget setOauthToken(java.lang.String oauthToken) {
      return (RemoveTarget) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveTarget setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveTarget) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveTarget setQuotaUser(java.lang.String quotaUser) {
      return (RemoveTarget) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveTarget setUserIp(java.lang.String userIp) {
      return (RemoveTarget) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String id;

    /**

     */
    public java.lang.String getId() {
      return id;
    }

    public RemoveTarget setId(java.lang.String id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveTarget set(String parameterName, Object value) {
      return (RemoveTarget) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateTarget".
   *
   * This request holds the parameters needed by the the targetendpoint server.  After setting any
   * optional parameters, call the {@link UpdateTarget#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.example.treasurehunt.targetendpoint.model.Target}
   * @return the request
   */
  public UpdateTarget updateTarget(com.example.treasurehunt.targetendpoint.model.Target content) throws java.io.IOException {
    UpdateTarget result = new UpdateTarget(content);
    initialize(result);
    return result;
  }

  public class UpdateTarget extends TargetendpointRequest<com.example.treasurehunt.targetendpoint.model.Target> {

    private static final String REST_PATH = "target";

    /**
     * Create a request for the method "updateTarget".
     *
     * This request holds the parameters needed by the the targetendpoint server.  After setting any
     * optional parameters, call the {@link UpdateTarget#execute()} method to invoke the remote
     * operation. <p> {@link
     * UpdateTarget#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.example.treasurehunt.targetendpoint.model.Target}
     * @since 1.13
     */
    protected UpdateTarget(com.example.treasurehunt.targetendpoint.model.Target content) {
      super(Targetendpoint.this, "PUT", REST_PATH, content, com.example.treasurehunt.targetendpoint.model.Target.class);
    }

    @Override
    public UpdateTarget setAlt(java.lang.String alt) {
      return (UpdateTarget) super.setAlt(alt);
    }

    @Override
    public UpdateTarget setFields(java.lang.String fields) {
      return (UpdateTarget) super.setFields(fields);
    }

    @Override
    public UpdateTarget setKey(java.lang.String key) {
      return (UpdateTarget) super.setKey(key);
    }

    @Override
    public UpdateTarget setOauthToken(java.lang.String oauthToken) {
      return (UpdateTarget) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateTarget setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateTarget) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateTarget setQuotaUser(java.lang.String quotaUser) {
      return (UpdateTarget) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateTarget setUserIp(java.lang.String userIp) {
      return (UpdateTarget) super.setUserIp(userIp);
    }

    @Override
    public UpdateTarget set(String parameterName, Object value) {
      return (UpdateTarget) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Targetendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Targetendpoint}. */
    @Override
    public Targetendpoint build() {
      return new Targetendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link TargetendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setTargetendpointRequestInitializer(
        TargetendpointRequestInitializer targetendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(targetendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
