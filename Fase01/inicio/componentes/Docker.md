# Docker 101
Hablar de Docker es realmente hablar de contenedores, así somo Github es una servicio que nos permite usar GIT (el sistema distribuido de control de versiones), Docker es una manera de utilizar Contenedores.
Esto quiere decir, que hay otras formas de usar contenedores.

```mermaid
classDiagram
    class GIT {
        <<interface>>
        +control de versiones
        +ramificación
        +fusión
        +historial
    }
    
    class Contenedores {
        <<interface>>
        +aislamiento
        +portabilidad
        +gestión de imágenes
        +orquestación
    }
    
    class GitHub {
        +repositorios
        +colaboración
        +CI/CD
        +pull requests
    }
    
    class GitLab {
        +DevOps integrado
        +pipelines
        +registros
    }
    
    class BitBucket {
        +integración Atlassian
        +permisos avanzados
    }
    
    class Docker {
        +contenedores ligeros
        +Docker Hub
        +Docker Compose
    }
    
    class Podman {
        +sin daemon
        +rootless
        +compatible OCI
    }
    
    class containerd {
        +runtime básico
        +usado por K8s
        +gestión de imágenes
    }
    
    GIT <|.. GitHub : implementa
    GIT <|.. GitLab : implementa
    GIT <|.. BitBucket : implementa
    
    Contenedores <|.. Docker : implementa
    Contenedores <|.. Podman : implementa
    Contenedores <|.. containerd : implementa
    
    style GIT fill:#e1f5ff
    style Contenedores fill:#e1f5ff
    style GitHub fill:#fff3cd
    style GitLab fill:#fff3cd
    style BitBucket fill:#fff3cd
    style Docker fill:#d4edda
    style Podman fill:#d4edda
    style containerd fill:#d4edda
```


## VM vs Contenedor

```mermaid
graph TB
    subgraph VM["🖥️ MÁQUINAS VIRTUALES (VMs)"]
        direction TB
        VM_App1["App A"]
        VM_App2["App B"]
        VM_App3["App C"]
        VM_Bins1["Binarios/Libs"]
        VM_Bins2["Binarios/Libs"]
        VM_Bins3["Binarios/Libs"]
        VM_OS1["Guest OS"]
        VM_OS2["Guest OS"]
        VM_OS3["Guest OS"]
        VM_Hypervisor["Hypervisor (VMware, VirtualBox, KVM)"]
        VM_HostOS["Sistema Operativo Host"]
        VM_Infrastructure["Infraestructura (Servidor Físico)"]
        
        VM_App1 --> VM_Bins1
        VM_App2 --> VM_Bins2
        VM_App3 --> VM_Bins3
        VM_Bins1 --> VM_OS1
        VM_Bins2 --> VM_OS2
        VM_Bins3 --> VM_OS3
        VM_OS1 --> VM_Hypervisor
        VM_OS2 --> VM_Hypervisor
        VM_OS3 --> VM_Hypervisor
        VM_Hypervisor --> VM_HostOS
        VM_HostOS --> VM_Infrastructure
    end
    
    subgraph Container["📦 CONTENEDORES"]
        direction TB
        C_App1["App A"]
        C_App2["App B"]
        C_App3["App C"]
        C_Bins1["Binarios/Libs"]
        C_Bins2["Binarios/Libs"]
        C_Bins3["Binarios/Libs"]
        C_Runtime["Container Runtime (Docker, containerd)"]
        C_HostOS["Sistema Operativo Host"]
        C_Infrastructure["Infraestructura (Servidor Físico)"]
        
        C_App1 --> C_Bins1
        C_App2 --> C_Bins2
        C_App3 --> C_Bins3
        C_Bins1 --> C_Runtime
        C_Bins2 --> C_Runtime
        C_Bins3 --> C_Runtime
        C_Runtime --> C_HostOS
        C_HostOS --> C_Infrastructure
    end
    
    style VM_OS1 fill:#ff9999
    style VM_OS2 fill:#ff9999
    style VM_OS3 fill:#ff9999
    style VM_Hypervisor fill:#ffcc99
    style VM_HostOS fill:#99ccff
    style VM_Infrastructure fill:#cccccc
    
    style C_Runtime fill:#99ff99
    style C_HostOS fill:#99ccff
    style C_Infrastructure fill:#cccccc
    
    style VM_App1 fill:#ffffcc
    style VM_App2 fill:#ffffcc
    style VM_App3 fill:#ffffcc
    style VM_Bins1 fill:#ffeecc
    style VM_Bins2 fill:#ffeecc
    style VM_Bins3 fill:#ffeecc
    
    style C_App1 fill:#ffffcc
    style C_App2 fill:#ffffcc
    style C_App3 fill:#ffffcc
    style C_Bins1 fill:#ffeecc
    style C_Bins2 fill:#ffeecc
    style C_Bins3 fill:#ffeecc
```
