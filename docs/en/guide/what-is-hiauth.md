# What is HiAuth? {#what-is-hiauth}

HiAuth is an authentication and authorization system based on the OAuth2.0 protocol. By integrating the HiAuth system, you can quickly enable authentication and authorization functions for your application. HiAuth supports SaaS mode, Docker private deployment mode, and source code compilation installation mode. Moreover, it is completely free and open-source.

<div class="tip custom-block" style="padding-top: 8px">

Want to give it a try? Jump to [Quick Start](./quick-start).

</div>

## Functions {#function}

### **1. Authentication**
Based on the `OAuth2.0` authorization protocol, it allows third-party applications to access user resources with user authorization, without sharing user credentials.
- **Core Process**:
    - **Authorization Code Mode**: Suitable for web applications, obtaining an access token through an authorization code exchange (most secure).
    - **Implicit Mode**: Suitable for Single Page Applications (SPAs), directly returning an access token (no refresh token).
    - **Password Mode**: Users directly provide their account and password to the client (for use only in trusted scenarios).
    - **Client Credentials Mode**: Applications obtain tokens directly with their own identity (for service-to-service communication).
- **Token Management**:
    - Access Token is used for resource access, with a relatively short validity period.
    - Refresh Token is used to obtain a new access token and must be securely stored.
- **Security**:
    - Enforces HTTPS transmission to prevent token leakage.
    - Token Binding to prevent man-in-the-middle attacks.

### **2. Access Authorization**
Access authorization (`Authorization`) determines whether users/applications can perform specific operations or access resources. It is based on `RBAC` (Role-Based Access Control), assigning permissions through roles (e.g., administrator, regular user).

### **3. Application Management**
Manages the integration and lifecycle of third-party or internal applications.
- **Core Functions**:
    - **Application Registration**: Assigns unique `ClientID`/`ClientSecret`, defining permission scopes.
    - **Key Rotation**: Regularly updates `ClientSecret` to prevent leakage risks.
    - **Permission Control**: Limits the API or data scope that applications can access.
- **Security Auditing**:
    - Logs application behavior (e.g., API call frequency).
    - Reviews application permission requests to ensure the principle of least privilege.

### **4. Organization Management**
Manages the hierarchical structure and member relationships of enterprises or teams.
- **Functions**:
    - **Multi-level Architecture**: Supports nested structures such as departments, teams, and project groups.
    - **Multi-tenancy Support**: Isolates data and permissions of different organizations (e.g., in SaaS scenarios).
    - **Administrator Assignment**: Sets organization administrators to manage members and permissions.

### **5. Permission Management**
Defines and manages the operations that users or roles can perform.
- **Core Mechanism**:
    - **Permission Granularity**: Supports control at the API level, functional level, or data field level.
    - **Permission Groups**: Packages permissions into templates (e.g., "Finance Approval Group").
    - **Permission Inheritance and Overrides**: Sub-roles inherit permissions from parent roles, with the ability to make local adjustments.
- **Audit and Compliance**:
    - Permission change logs: Records who modified permissions and when.
    - Regularly reviews permission assignments to avoid redundancy or excessive authorization.

### **6. User Management**
Manages user identities, authentication, and lifecycle.
- **Core Functions**:
    - **Identity Storage**: Supports local databases.
    - **Lifecycle Management**: User registration, disabling, deletion, and profile updates.
    - **Authentication Enhancement**: Multi-Factor Authentication (MFA), Single Sign-On (SSO).


## Video Tutorial
<iframe src="//player.bilibili.com/player.html?bvid=BV1KhZEYmEU9&page=1" allowfullscreen></iframe>