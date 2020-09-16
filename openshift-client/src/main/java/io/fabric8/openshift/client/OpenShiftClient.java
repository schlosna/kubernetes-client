/**
 * Copyright (C) 2015 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fabric8.openshift.client;

import io.fabric8.kubernetes.api.model.KubernetesList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.VersionInfo;
import io.fabric8.kubernetes.client.dsl.*;
import io.fabric8.openshift.api.model.*;
import io.fabric8.openshift.api.model.DoneableBuild;
import io.fabric8.openshift.api.model.DoneableBuildConfig;
import io.fabric8.openshift.api.model.DoneableClusterNetwork;
import io.fabric8.openshift.api.model.DoneableClusterRoleBinding;
import io.fabric8.openshift.api.model.DoneableDeploymentConfig;
import io.fabric8.openshift.api.model.DoneableEgressNetworkPolicy;
import io.fabric8.openshift.api.model.DoneableGroup;
import io.fabric8.openshift.api.model.DoneableImage;
import io.fabric8.openshift.api.model.DoneableImageStream;
import io.fabric8.openshift.api.model.DoneableImageStreamTag;
import io.fabric8.openshift.api.model.DoneableImageTag;
import io.fabric8.openshift.api.model.DoneableNetNamespace;
import io.fabric8.openshift.api.model.DoneableOAuthAccessToken;
import io.fabric8.openshift.api.model.DoneableOAuthAuthorizeToken;
import io.fabric8.openshift.api.model.DoneableOAuthClient;
import io.fabric8.openshift.api.model.DoneableRangeAllocation;
import io.fabric8.openshift.api.model.DoneableRole;
import io.fabric8.openshift.api.model.DoneableRoleBinding;
import io.fabric8.openshift.api.model.DoneableRoute;
import io.fabric8.openshift.api.model.DoneableSecurityContextConstraints;
import io.fabric8.openshift.api.model.DoneableSubjectAccessReview;
import io.fabric8.openshift.api.model.DoneableTemplate;
import io.fabric8.openshift.api.model.DoneableUser;
import io.fabric8.openshift.client.dsl.*;

import java.net.URL;

public interface OpenShiftClient extends KubernetesClient {

  /**
   * Get Url of the cluster
   *
   * @return {@link java.net.URL} of OpenShift Cluster
   */
  URL getOpenshiftUrl();

  /**
   * API entrypoint for accessing OpenShift config APIGroup resources(config.openshift.io/v1)
   *
   * @return {@link OpenShiftConfigAPIGroupDSL} which contains respective resources in this API group
   */
  OpenShiftConfigAPIGroupDSL config();

  /**
   * API entrypoint for accessing OpenShift console APIGroup resources(console.openshift.io/v1)
   *
   * @return {@link OpenShiftConsoleAPIGroupDSL} which contains respective resources in this API group
   */
  OpenShiftConsoleAPIGroupDSL console();

  /**
   * API entrypoint for accessing OpenShift operator APIGroup resources(operator.openshift.io/v1 and operator.openshift.io/v1alpha1)
   *
   * @return {@link OpenShiftOperatorAPIGroupDSL} which contains respective resources in this API group
   */
  OpenShiftOperatorAPIGroupDSL operator();

  /**
   * API entrypoint for accessing OpenShift operator hub APIGroup resources(operator.coreos.com/v1alpha1)
   *
   * @return {@link OpenShiftOperatorHubAPIGroupDSL} which contains respective resources in this API group
   */
  OpenShiftOperatorHubAPIGroupDSL operatorHub();

  /**
   * {@inheritDoc}
   */
  ExtensionsAPIGroupDSL extensions();

  /**
   * {@inheritDoc}
   */
  VersionInfo getVersion();

  /**
   * {@inheritDoc}
   */
  AppsAPIGroupDSL apps();

  /**
   * {@inheritDoc}
   */
  AutoscalingAPIGroupDSL autoscaling();

  /**
   * API entrypoint for accessing OpenShift operator APIGroup resources(monitoring.coreos.com/v1)
   *
   * @return {@link OpenShiftMonitoringAPIGroupDSL} which contains respective resources in this API group
   */
  OpenShiftMonitoringAPIGroupDSL monitoring();

  /**
   * API entrypoint for handling NetNamespace(network.openshift.io/v1)
   *
   * @return NonNamespaceOperation instance for NetNamespace object
   */
  NonNamespaceOperation<NetNamespace, NetNamespaceList, DoneableNetNamespace, Resource<NetNamespace, DoneableNetNamespace>> netNamespaces();

  /**
   * API entrypoint for handling ClusterNetwork(network.openshift.io/v1)
   *
   * @return NonNamespaceOperation instance for ClusterNetwork object
   */
  NonNamespaceOperation<ClusterNetwork, ClusterNetworkList, DoneableClusterNetwork, Resource<ClusterNetwork, DoneableClusterNetwork>> clusterNetworks();

  /**
   * API entrypoint for handling EgressNetworkPolicy(network.openshift.io/v1)
   *
   * @return MixedOperation instance for EgressNetworkPolicy object
   */
  MixedOperation<EgressNetworkPolicy, EgressNetworkPolicyList, DoneableEgressNetworkPolicy, Resource<EgressNetworkPolicy, DoneableEgressNetworkPolicy>> egressNetworkPolicies();

  /**
   * {@inheritDoc}
   */
  NetworkAPIGroupDSL network();

  /**
   * {@inheritDoc}
   */
  StorageAPIGroupDSL storage();

  /**
   * {@inheritDoc}
   */
  BatchAPIGroupDSL batch();

  /**
   * {@inheritDoc}
   */
  RbacAPIGroupDSL rbac();

  /**
   * {@inheritDoc}
   */
  SchedulingAPIGroupDSL scheduling();

  /**
   * {@inheritDoc}
   */
  SettingsAPIGroupDSL settings();

  /**
   * API entrypoint for handling Build(build.openshift.io/v1)
   *
   * @return MixedOperation instance for Build object
   */
  MixedOperation<Build, BuildList, DoneableBuild, BuildResource<Build, DoneableBuild, String, LogWatch>> builds();

  /**
   * API entrypoint for handling BuildConfig(build.openshift.io/v1)
   *
   * @return MixedOperation instance for BuildConfig object
   */
  MixedOperation<BuildConfig, BuildConfigList, DoneableBuildConfig, BuildConfigResource<BuildConfig, DoneableBuildConfig, Void, Build>> buildConfigs();

  /**
   * API entrypoint for handling DeploymentConfig(apps.openshift.io/v1)
   *
   * @return MixedOperation instance for DeploymentConfig object
   */
  MixedOperation<DeploymentConfig, DeploymentConfigList, DoneableDeploymentConfig, DeployableScalableResource<DeploymentConfig, DoneableDeploymentConfig>> deploymentConfigs();

  /**
   * API entrypoint for handling Group(user.openshift.io/v1)
   *
   * @return NonNamespaceOperation instance for Group object
   */
  NonNamespaceOperation<Group, GroupList, DoneableGroup, Resource<Group, DoneableGroup>> groups();

  /**
   * API entrypoint for accessing Image(image.openshift.io/v1)
   *
   * @return Non Namespace Operation object for Image
   */
  NonNamespaceOperation<Image, ImageList, DoneableImage, Resource<Image, DoneableImage>> images();

  /**
   * API entrypoint for accessing ImageTag(image.openshift.io/v1)
   *
   * @return MixedOperation object for ImageTag
   */
  MixedOperation<ImageTag, ImageTagList, DoneableImageTag, Resource<ImageTag, DoneableImageTag>> imageTags();

  /**
   * API entrypoint for accessing ImageStream(image.openshift.io/v1)
   *
   * @return MixedOperation object for ImageStream
   */
  MixedOperation<ImageStream, ImageStreamList, DoneableImageStream, Resource<ImageStream, DoneableImageStream>> imageStreams();

  /**
   * API entrypoint for accessing ImageStreamTag(image.openshift.io/v1)
   *
   * @return MixedOperation object for ImageStreamTag
   */
  MixedOperation<ImageStreamTag, ImageStreamTagList, DoneableImageStreamTag, Resource<ImageStreamTag, DoneableImageStreamTag>> imageStreamTags();

  /**
   * API entrypoint for accessing OAuthAccessToken(oauth.openshift.io/v1)
   *
   * @return NonNamespaceOperation object for OAuthAccessToken
   */
  NonNamespaceOperation<OAuthAccessToken, OAuthAccessTokenList, DoneableOAuthAccessToken, Resource<OAuthAccessToken, DoneableOAuthAccessToken>> oAuthAccessTokens();

  /**
   * API entrypoint for accessing OAuthAuthorizeToken(oauth.openshift.io/v1)
   *
   * @return NonNamespaceOperation object for OAuthAuthorizeToken
   */
  NonNamespaceOperation<OAuthAuthorizeToken, OAuthAuthorizeTokenList, DoneableOAuthAuthorizeToken, Resource<OAuthAuthorizeToken, DoneableOAuthAuthorizeToken>> oAuthAuthorizeTokens();

  /**
   * API entrypoint for accessing OAuthClient(oauth.openshift.io/v1)
   *
   * @return NonNamespaceOperation object for OAuthClient
   */
  NonNamespaceOperation<OAuthClient, OAuthClientList, DoneableOAuthClient, Resource<OAuthClient, DoneableOAuthClient>> oAuthClients();

  /**
   * API entrypoint for accessing Project operations(project.openshift.io/v1)
   *
   * @return {@link ProjectOperation} for Project specific operations
   */
  ProjectOperation projects();

  /**
   * API entrypoint for accessing ProjectRequest operations(project.openshift.io/v1)
   *
   * @return {@link ProjectRequestOperation} for ProjectRequest specific operations
   */
  ProjectRequestOperation projectrequests();

  /**
   * API entrypoint for accessing OpenShift Quota APIGroup resources(quota.openshift.io/v1)
   *
   * @return {@link OpenShiftQuotaAPIGroupDSL} which contains operations for respective resources inside the APIGroup
   */
  OpenShiftQuotaAPIGroupDSL quotas();

  /**
   * API entrypoint for accessing Role(authorization.openshift.io/v1)
   *
   * @return MixedOperation object for Role
   */
  MixedOperation<Role, RoleList, DoneableRole, Resource<Role, DoneableRole>> roles();

  /**
   * API entrypoint for accessing RoleBinding(authorization.openshift.io/v1)
   *
   * @return MixedOperation object for RoleBinding
   */
  MixedOperation<RoleBinding, RoleBindingList, DoneableRoleBinding, Resource<RoleBinding, DoneableRoleBinding>>
  roleBindings();

  /**
   * API entrypoint for accessing Route(route.openshift.io/v1)
   *
   * @return MixedOperation object for Route
   */
  MixedOperation<Route, RouteList, DoneableRoute, Resource<Route, DoneableRoute>> routes();

  /**
   * API entrypoint for accessing Template(template.openshift.io/v1)
   *
   * @return {@link ParameterMixedOperation} object for Template operations
   */
  ParameterMixedOperation<Template, TemplateList, DoneableTemplate, TemplateResource<Template, KubernetesList, DoneableTemplate>> templates();

  /**
   * API entrypoint for accessing User(user.openshift.io/v1)
   *
   * @return NonNamespaceOperation object for User
   */
  NonNamespaceOperation<User, UserList, DoneableUser, Resource<User, DoneableUser>> users();

  /**
   * API entrypoint for accessing RangeAllocation(security.openshift.io/v1)
   *
   * @return NonNamespaceOperation object for RangeAllocation
   */
  NonNamespaceOperation<RangeAllocation, RangeAllocationList, DoneableRangeAllocation, Resource<RangeAllocation, DoneableRangeAllocation>> rangeAllocations();

  /**
   * API entrypoint for accessing SecurityContextConstraints(security.openshift.io/v1)
   *
   * @return NonNamespaceOperation object for SecurityContextConstraints
   */
  NonNamespaceOperation<SecurityContextConstraints, SecurityContextConstraintsList, DoneableSecurityContextConstraints, Resource<SecurityContextConstraints, DoneableSecurityContextConstraints>> securityContextConstraints();

  Createable<SubjectAccessReview, SubjectAccessReviewResponse, DoneableSubjectAccessReview> subjectAccessReviews();

  OpenShiftLocalSubjectAccessReviewOperationsImpl localSubjectAccessReviews();

  /**
   * API entrypoint for accessing ClusterRoleBinding(authorization.openshift.io/v1)
   *
   * @return MixedOperation object for ClusterRoleBinding
   */
  MixedOperation<ClusterRoleBinding, ClusterRoleBindingList, DoneableClusterRoleBinding, Resource<ClusterRoleBinding, DoneableClusterRoleBinding>> clusterRoleBindings();

  /**
   * Configure Request Config
   *
   * @param requestConfig request configuration for connection
   * @return {@link NamespacedOpenShiftClient} configured with specified RequestConfig
   */
  FunctionCallable<NamespacedOpenShiftClient> withRequestConfig(RequestConfig requestConfig);

  /**
   * Returns the current logged in user details similar to the `oc whoami` command.
   * @return User as currently logged in user
   */
  User currentUser();

  /**
   * Returns true if this cluster is a legacy openshift cluster or supports the given OpenShift API Group defined in {@link OpenShiftAPIGroups}
   *
   * @param apiGroup API group as string
   * @return boolean value indicating cluster is legacy or supports APIGroups
   */
  boolean supportsOpenShiftAPIGroup(String apiGroup);
}
